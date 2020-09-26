package com.swpu.hth.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.swpu.hth.dao.StateDao;
import com.swpu.hth.entity.DestributAlgorithm.CarToPark;
import com.swpu.hth.entity.DestributAlgorithm.CarToParkExpense;
import com.swpu.hth.entity.OrderReDO;
import com.swpu.hth.entity.ParkingLotInfoDO;
import com.swpu.hth.service.OrderService;
import com.swpu.hth.service.ParkingLotInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private StateDao stateDao;

    @Autowired
    private ParkingLotInfoService parkingLotInfoService;

    /*停车场信息表，包括名字，经纬度，泊车率等*/
    private ArrayList<ParkingLotInfoDO> parkingLotInfoDOArrayListAll;
    private ArrayList<ParkingLotInfoDO> parkingLotInfoDOArrayListLow;

    /*待分配预约信息表*/
    private ArrayList<OrderReDO> orderReDOArrayList;

    /*停车场总分配矩阵GrAll*/
    private int[] GrAll = new int[6];
    /*All链表剔除掉Opt链表后（即Dis链表），上层模型分配的GrAll矩阵剩余的车位数数组*/
    private int[] GrRem = new int[6];
    /*CarToParkArrayListDis对应的用户分配数组，可能是对stack <carToParkExpense>重新排序过的*/
    private int[] GrDis = new int[6];
    /*存储分配优数组*/
    private int[] Gr = {200,200,100,100,100,100};

    /*用户车位分配矩阵*/
    private int[][] X = new int[300][6];
    /*用户--各停车场总花销表*/
    private ArrayList<CarToPark> carToParkArrayList;
    /*用户--可分配停车场只有一个*/
    private ArrayList<CarToPark> carToParkArrayListOpt;
    /*用户--可分配停车场超过一个*/
    private  ArrayList<CarToPark> carToParkArrayListDis;
    /*用来记录每一次迭代需要多少个用户改变，将它们记录下来并放在Temp表，若数据成功优化则直接清空，否则栈回滚后清空*/
    private  ArrayList<CarToPark> carToParkArrayListASJ;
    private  ArrayList<CarToPark> carToParkArrayListSTYG;
    private  ArrayList<CarToPark> carToParkArrayListLFS;
    private  ArrayList<CarToPark> carToParkArrayListWWY;
    private  ArrayList<CarToPark> carToParkArrayListDLGJ;
    private  ArrayList<CarToPark> carToParkArrayListHYGF;

    /**
     *
     * @param username 用户名
     * @param license 车牌
     * @param r1 车主时间转换率
     * @param time_re 时间
     * @return      -1表示车位已经占用
     *              不为-1就返回的是自增id，说明成功了，将这个数据存储，过一会存到orderHistory是需要删除这个记录
     * @throws Exception
     */
    @PostMapping("/createOrder")
    public void saveOrder(@RequestParam("username") String username,
                             @RequestParam("license") String license,
                             @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") @RequestParam("time_re") String time_re,
                             @RequestParam float r1,
                             @RequestParam double lat,
                             @RequestParam double lng) throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(time_re);
        System.out.println(date);

        String [] yan ={
                "1","2","3","4","5","6","7","8","9","0",
                "A","B","C","D","E","F","G","H",
                "I","J","K","L","M","N","O","P","Q",
                "R","S","T","U","V","W","X","Y","Z",};
        int a,b,c,d,e,tr;

        for (int i=0;i<300;i++){
            a = (int)(Math.random()*36);
            b = (int)(Math.random()*36);
            c = (int)(Math.random()*36);
            d = (int)(Math.random()*36);
            e = (int)(Math.random()*36);
            String yanzhengma = yan[a]+yan[b]+yan[c]+yan[d]+yan[e];
            String license1;
            license1 = license+yanzhengma;
            tr = (int)Math.random()*2+1;
            orderService.createOrder(username,license1,date,r1,tr,lat,lng);
        }


//        List<ParkingLotInfoDO> parkingLotInfoDOList = parkingLotInfoService.queryParkingLotInfo();
//        Stack<CarToParkExpense> stack = new Stack<>();
//        CarToParkExpense carToParkExpense = new CarToParkExpense();
//        for(int i=0;i<parkingLotInfoDOList.size();i++){
//           carToParkExpense.setParkingLotName(parkingLotInfoDOList.get(i).getParking_name());
//           carToParkExpense.setExpense((float) (r1*((lat-parkingLotInfoDOList.get(i).getLat())+lng-parkingLotInfoDOList.get(i).getLng()) + tr*parkingLotInfoDOList.get(i).getParking_fee()));
//           stack.add(carToParkExpense);
//        }
//        sortStackByStack(stack);
//        System.out.println(stack);

    }

    /**
     *  更新用户的lat，lng。简化手动输入
     */
    @GetMapping("/updateOrder")
    public void upDateOrder() throws Exception {
        int mLat,mLng;
        for(int i =553;i<570;i++){
            mLat = (int)((104.073478+Math.random()*0.0017)*1000000);
            mLng = (int)((30.635647+Math.random()*0.0006)*1000000);
            orderService.updateOrder(mLat/1000000d,mLng/1000000d,i);
        }

//        for(int i =328;i<348;i++){
//            mLat = (int)((104.072637+Math.random()*0.0001)*1000000);
//            mLng = (int)((30.635119+Math.random()*0.0001)*1000000);
//            orderService.updateOrder(mLat/1000000d,mLng/1000000d,i);
//        }
//        for(int i =348;i<378;i++){
//            mLat = (int)((104.071223+Math.random()*0.0006)*1000000);
//            mLng = (int)((30.637014+Math.random()*0.0003)*1000000);
//            orderService.updateOrder(mLat/1000000d,mLng/1000000d,i);
//        }
//        for(int i =378;i<408;i++){
//            mLat = (int)((104.071107+Math.random()*0.0008)*1000000);
//            mLng = (int)((30.63816+Math.random()*0.0007)*1000000);
//            orderService.updateOrder(mLat/1000000d,mLng/1000000d,i);
//        }
//        for(int i =408;i<448;i++){
//            mLat = (int)((104.072822+Math.random()*0.0001)*1000000);
//            mLng = (int)((30.639982+Math.random()*0.0002)*1000000);
//            orderService.updateOrder(mLat/1000000d,mLng/1000000d,i);
//        }
//
//        for(int i =448;i<458;i++){
//            mLat = (int)((104.07293+Math.random()*0.0003)*1000000);
//            mLng = (int)((30.637585+Math.random()*0.0004)*1000000);
//            orderService.updateOrder(mLat/1000000d,mLng/1000000d,i);
//        }
//        for(int i =458;i<478;i++){
//            mLat = (int)((104.073231+Math.random()*0.0012)*1000000);
//            mLng = (int)((30.638945+Math.random()*0.0003)*1000000);
//            orderService.updateOrder(mLat/1000000d,mLng/1000000d,i);
//        }
//        for(int i =478;i<490;i++){
//            mLat = (int)((104.073581+Math.random()*0.0005)*1000000);
//            mLng = (int)((30.637989+Math.random()*0.0004)*1000000);
//            orderService.updateOrder(mLat/1000000d,mLng/1000000d,i);
//        }
//        for(int i =490;i<543;i++){
//            mLat = (int)((104.073442+Math.random()*0.0016)*1000000);
//            mLng = (int)((30.637546+Math.random()*0.0003)*1000000);
//            orderService.updateOrder(mLat/1000000d,mLng/1000000d,i);
//        }
//        for(int i =543;i<553;i++){
//            mLat = (int)((104.073433+Math.random()*0.0014)*1000000);
//            mLng = (int)((30.636424+Math.random()*0.0003)*1000000);
//            orderService.updateOrder(mLat/1000000d,mLng/1000000d,i);
//        }
//        for(int i =553;i<598;i++){
//            mLat = (int)((104.073478+Math.random()*0.0017)*1000000);
//            mLng = (int)((30.635647+Math.random()*0.0006)*1000000);
//            orderService.updateOrder(mLat/1000000d,mLng/1000000d,i);
//        }
//        for(int i =598;i<618;i++){
//            mLat = (int)((104.073572+Math.random()*0.0007)*1000000);
//            mLng = (int)((30.635096+Math.random()*0.0004)*1000000);
//            orderService.updateOrder(mLat/1000000d,mLng/1000000d,i);
//        }
//        for(int i =618;i<628;i++){
//            mLat = (int)((104.073572+Math.random()*0.0007)*1000000);
//            mLng = (int)((30.635096+Math.random()*0.0004)*1000000);
//            orderService.updateOrder(mLat/1000000d,mLng/1000000d,i);
//        }
    }


    @GetMapping("/distribute")
    public void distributeCarOrder() throws Exception{
        parkingLotInfoDOArrayListAll = parkingLotInfoService.queryParkingLotInfo();

        /*总泊车率*/
        float RateParkingOrderAll ;
        int DenominatorRateParkingOrderAll = 0; //分母
        int NumeratorRateParkingOrderAll = 0;  //分子

        for(int i=0;i<parkingLotInfoDOArrayListAll.size();i++){
            NumeratorRateParkingOrderAll = NumeratorRateParkingOrderAll + parkingLotInfoDOArrayListAll.get(i).getN_occupy();
            DenominatorRateParkingOrderAll = DenominatorRateParkingOrderAll + parkingLotInfoDOArrayListAll.get(i).getN_total();
        }
        RateParkingOrderAll = (NumeratorRateParkingOrderAll+300)*1.0f/DenominatorRateParkingOrderAll;

        //初始化List，不然会空指针
        String parkingName;
        parkingLotInfoDOArrayListLow = new ArrayList<>();
        for(int i=0;i<parkingLotInfoDOArrayListAll.size();i++){
            if(RateParkingOrderAll*100>parkingLotInfoDOArrayListAll.get(i).getRate_parking()){
                //注意，这里的remove(i) 方法会与循环体i产生冲突,所以需要重新设置一个List.利用add方法来重新设置低停泊率表
                System.out.println(parkingLotInfoDOArrayListAll.get(i).getParking_name());
                parkingLotInfoDOArrayListLow.add(parkingLotInfoDOArrayListAll.get(i));
            }else{
                parkingName = parkingLotInfoDOArrayListAll.get(i).getParking_name();
                switch (parkingName){
                    case "A世界":
                        GrAll[0] = 0;
                        break;
                    case "省体育馆":
                        GrAll[1] = 0;
                        break;
                    case "来福士":
                        GrAll[2] = 0;
                        break;
                    case "文物院":
                        GrAll[3] = 0;
                        break;
                    case "大陆国际":
                        GrAll[4] = 0;
                        break;
                    case"华宇国府":
                        GrAll[5] = 0;
                }
            }
        }
        /*剔除泊车率过高的停车场之后的泊车率*/
        float RateParkingOrderLow;
        int DenominatorRateParkingOrderLow = 0; //分母
        int NumeratorRateParkingOrderLow = 0;  //分子

        for(int i=0;i<parkingLotInfoDOArrayListLow.size();i++){
            NumeratorRateParkingOrderLow = NumeratorRateParkingOrderLow + parkingLotInfoDOArrayListLow.get(i).getN_occupy();
            DenominatorRateParkingOrderLow = DenominatorRateParkingOrderLow + parkingLotInfoDOArrayListLow.get(i).getN_total();
        }
        System.out.println(NumeratorRateParkingOrderLow);
        System.out.println(DenominatorRateParkingOrderLow);
        RateParkingOrderLow = (NumeratorRateParkingOrderLow+300)*1.0f/DenominatorRateParkingOrderLow;
        System.out.println(RateParkingOrderAll+"--"+RateParkingOrderLow);

        //计算初始上层最优分配数组Gr
        int tempGr;
        /*计算已分配数，如果四舍五入会出现问题，不够300在分配*/
        int tempG =0;
        int random;
        String parkingNameLow;
        for(int i=0;i<parkingLotInfoDOArrayListLow.size();i++){
             tempGr = (int)(parkingLotInfoDOArrayListLow.get(i).getN_total()*RateParkingOrderLow) - parkingLotInfoDOArrayListLow.get(i).getN_occupy();
             tempG = tempG+tempGr;
             parkingNameLow = parkingLotInfoDOArrayListLow.get(i).getParking_name();
             switch (parkingNameLow){
                 case "A世界":
                     GrAll[0] = tempGr;
                     break;
                 case "省体育馆":
                     GrAll[1] = tempGr;
                     break;
                 case "来福士":
                     GrAll[2] = tempGr;
                     break;
                 case "文物院":
                     GrAll[3] = tempGr;
                     break;
                 case "大陆国际":
                     GrAll[4] = tempGr;
                     break;
                 case"华宇国府":
                     GrAll[5] = tempGr;
             }
        }
        for(int i=0;i<(300-tempG);i++){
            GrAll[2]++;
        }
        //初始化Gr,后续将Gr作为剔除备选车厂唯一的i之后的分配矩阵
        GrRem = GrAll;
        System.out.println("GrAll数组"+GrAll[0]+" "+ GrAll[1]+" "+ GrAll[2]+" "+ GrAll[3]+" "+ GrAll[4]+" "+ GrAll[5]);

        //计算初始下层用户最优分配模型
        orderReDOArrayList = orderService.queryReOrder();
        carToParkArrayList = new ArrayList<>();
        carToParkArrayListOpt = new ArrayList<>();
        carToParkArrayListDis = new ArrayList<>();
        /*设置用户信息*/
        float r1,tr;
        double lat,lng;
        /*设置用户目标车场排行表的前置*/
        CarToPark carToPark;
        CarToParkExpense carToParkExpense;
        for(int i=0;i<orderReDOArrayList.size();i++){
            /*注意初始化catToPark和carToParkExpense时候的作用域问题*/
            carToPark = new CarToPark();
            /*设置当前的用户（第i个）信息*/
            r1 = orderReDOArrayList.get(i).getR1();
            tr = orderReDOArrayList.get(i).getTr();
            lat = orderReDOArrayList.get(i).getLat();
            lng = orderReDOArrayList.get(i).getLng();
            for(int j =0;j<parkingLotInfoDOArrayListAll.size();j++){
                /*注意初始化catToPark和carToParkExpense时候的作用域问题*/
                carToParkExpense = new CarToParkExpense();
                String stringtemp;
                double temp;
                stringtemp = parkingLotInfoDOArrayListAll.get(j).getParking_name();
                temp =  r1*((Math.abs(parkingLotInfoDOArrayListAll.get(j).getLat()-lat)
                        + Math.abs(parkingLotInfoDOArrayListAll.get(j).getLng()-lng))*28.83f)
                        + parkingLotInfoDOArrayListAll.get(j).getParking_fee()*tr;
                carToParkExpense.setParkingLotName(stringtemp);
                carToParkExpense.setExpense((float)temp);
                carToPark.getCarToParkExpenseStack().push(carToParkExpense);
            }
            sortStackByStack(carToPark.getCarToParkExpenseStack());
            selectStackByStack(carToPark.getCarToParkExpenseStack());
            carToPark.setUsername(orderReDOArrayList.get(i).getUsername())
                    .setLicense(orderReDOArrayList.get(i).getLicense());
            carToParkArrayList.add(carToPark);
        }


        //打印用户最想去的停车场分布矩阵SS
        int[] ss = new int[6];
        getArrayByArrayList(ss,carToParkArrayList);
        System.out.println("用户最想去"+ss[0]+" " + ss[1]+" " + ss[2]+" " + ss[3]+" " + ss[4]+" " + ss[5]);

        /*区分备选停车场唯一和不唯一的用户，为了减少迭代次数*/
        for(int i=0;i<carToParkArrayList.size();i++){
            if (carToParkArrayList.get(i).carToParkExpenseStack.size()==1){
                carToParkArrayListOpt.add(carToParkArrayList.get(i));
            }else {
                carToParkArrayListDis.add(carToParkArrayList.get(i));
            }
        }

        /*测试是否得出了各用户的最优停车场排序栈*/
//        for(int i=0;i<carToParkArrayList.size();i++){
//            if(carToParkArrayList.get(i).carToParkExpenseStack.peek().getParkingLotName().equals("A世界")){
//                System.out.println(carToParkArrayList.get(i).getLicense());
//                while(!carToParkArrayList.get(i).carToParkExpenseStack.isEmpty()){
//                    CarToParkExpense carToParkExpense1 = carToParkArrayList.get(i).carToParkExpenseStack.pop();
//                    System.out.println(carToParkExpense1.getParkingLotName()+"---"+carToParkExpense1.getExpense());
//                }
//                System.out.println();
//            }
//        }

         for(int i=0;i<carToParkArrayListOpt.size();i++){
            switch (carToParkArrayListOpt.get(i).getCarToParkExpenseStack().peek().getParkingLotName()){
                case "A世界":
                    GrRem[0]--;
                    break;
                case "省体育馆":
                    GrRem[1]--;
                    break;
                case "来福士":
                    GrRem[2]--;
                    break;
                case "文物院":
                    GrRem[3]--;
                    break;
                case "大陆国际":
                    GrRem[4]--;
                    break;
                case "华宇国府":
                    GrRem[5]--;
                    break;
            }
        }
        System.out.println("GrRem数组"+GrRem[0]+" " + GrRem[1]+" " + GrRem[2]+" " + GrRem[3]+" " + GrRem[4]+" " + GrRem[5]);

        /*计算一下用户最优总花销*/
        float yhzy = 0.0f;
        for (int i=0;i<carToParkArrayListDis.size();i++) {
            if(carToParkArrayList.get(i).carToParkExpenseStack.peek().getParkingLotName().equals("来福士")){
                if((float)Math.random()>0.8){
                    yhzy = yhzy+carToParkArrayListDis.get(i).carToParkExpenseStack.peek().getExpense()+10;
                }else {
                    yhzy = yhzy+carToParkArrayListDis.get(i).carToParkExpenseStack.peek().getExpense();
                }
            }else {
                yhzy = yhzy+carToParkArrayListDis.get(i).carToParkExpenseStack.peek().getExpense();
            }
        }
        for (int i=0;i<carToParkArrayListOpt.size();i++) {
            yhzy = yhzy+carToParkArrayListOpt.get(i).carToParkExpenseStack.peek().getExpense();
        }
        System.out.println("用户最优总："+yhzy);

         /*开始第二步分配*/
        int k = 0;
        int[] PSO_v = new int[6];
        carToParkArrayListASJ = new ArrayList<>();
        carToParkArrayListSTYG = new ArrayList<>();
        carToParkArrayListLFS = new ArrayList<>();
        carToParkArrayListWWY = new ArrayList<>();
        carToParkArrayListDLGJ = new ArrayList<>();
        carToParkArrayListHYGF = new ArrayList<>();
        /*初始化粒子、速度，同时记录stackTemp以便于回滚*/
        for(int i=0;i<carToParkArrayListDis.size();i++){
            carToParkArrayListDis.get(i).setStackTemp(randomSortStackByStack(carToParkArrayListDis.get(i).carToParkExpenseStack));
        }
        getArrayByArrayList(GrDis,carToParkArrayListDis);

        while(k<1000){
            for(int i=0;i<PSO_v.length;i++){
                PSO_v[i] = (GrRem[i]- GrDis[i])>0 ? 0 : (GrRem[i]- GrDis[i])*(1000-k)/1000/2;
            }
            for (int i=0;i<GrDis.length;i++){
                GrDis[i]=0;
            }
            /*根据速度、方向做一次运算*/
            for (int i = 0; i < carToParkArrayListDis.size(); i++) {
                switch (carToParkArrayListDis.get(i).carToParkExpenseStack.peek().getParkingLotName()){
                    case "A世界":
                        carToParkArrayListASJ.add(carToParkArrayListDis.get(i));
                        break;
                    case "省体育馆":
                        carToParkArrayListSTYG.add(carToParkArrayListDis.get(i));
                        break;
                    case "来福士":
                        carToParkArrayListLFS.add(carToParkArrayListDis.get(i));
                        break;
                    case "文物院":
                        carToParkArrayListWWY.add(carToParkArrayListDis.get(i));
                        break;
                    case "大陆国际":
                        carToParkArrayListDLGJ.add(carToParkArrayListDis.get(i));
                        break;
                    case "华宇国府":
                        carToParkArrayListHYGF.add(carToParkArrayListDis.get(i));
                        break;
                }
            }
            for (int i = 0; i < PSO_v[0] ; i++) {
                carToParkArrayListASJ.get(i).setStackTemp(randomSortStackByStack(carToParkArrayListASJ.get((int)Math.random()*carToParkArrayListASJ.size()).carToParkExpenseStack));
            }
            for (int i = 0; i < PSO_v[1] ; i++) {
                carToParkArrayListSTYG.get(i).setStackTemp(randomSortStackByStack(carToParkArrayListSTYG.get((int)Math.random()*carToParkArrayListSTYG.size()).carToParkExpenseStack));
            }
            for (int i = 0; i < PSO_v[2] ; i++) {
                carToParkArrayListLFS.get(i).setStackTemp(randomSortStackByStack(carToParkArrayListLFS.get((int)Math.random()*carToParkArrayListLFS.size()).carToParkExpenseStack));
            }
            for (int i = 0; i < PSO_v[3] ; i++) {
                carToParkArrayListWWY.get(i).setStackTemp(randomSortStackByStack(carToParkArrayListWWY.get((int)Math.random()*carToParkArrayListWWY.size()).carToParkExpenseStack));
            }
            for (int i = 0; i < PSO_v[4] ; i++) {
                carToParkArrayListDLGJ.get(i).setStackTemp(randomSortStackByStack(carToParkArrayListDLGJ.get((int)Math.random()*carToParkArrayListDLGJ.size()).carToParkExpenseStack));
            }
            for (int i = 0; i < PSO_v[5] ; i++) {
                carToParkArrayListHYGF.get(i).setStackTemp(randomSortStackByStack(carToParkArrayListHYGF.get((int)Math.random()*carToParkArrayListHYGF.size()).carToParkExpenseStack));
            }
            getArrayByArrayList(GrDis,carToParkArrayListDis);
            /*计算GrDis数组*/
            if(compareArray(GrDis,GrRem)<compareArray(Gr,GrRem)){
                for (int i=0;i<GrDis.length;i++){
                    Gr[i]=GrDis[i];
                }
            }else {
                for(int i=0;i<carToParkArrayListDis.size();i++){
                    recoverStackBytemp(carToParkArrayListDis.get(i).getStackTemp(),carToParkArrayListDis.get(i).carToParkExpenseStack);
                }
            }
            for (int i=0;i<GrDis.length;i++){
                GrDis[i]=0;
            }
            carToParkArrayListASJ.clear();
            carToParkArrayListSTYG.clear();
            carToParkArrayListLFS.clear();
            carToParkArrayListWWY.clear();
            carToParkArrayListDLGJ.clear();
            carToParkArrayListHYGF.clear();
            k++;
            }
        getArrayByArrayList(Gr,carToParkArrayListOpt);
        System.out.println("最终分配组"+Gr[0]+" "+Gr[1]+" "+Gr[2]+" "+Gr[3]+" "+Gr[4]+" "+Gr[5]);



        /*计算一下用户最优总花销*/
        float ooo = 0.0f;
        for (int i=0;i<carToParkArrayListDis.size();i++) {
            ooo = ooo+carToParkArrayListDis.get(i).carToParkExpenseStack.peek().getExpense();
        }
        for (int i=0;i<carToParkArrayListOpt.size();i++) {
            ooo = ooo+carToParkArrayListOpt.get(i).carToParkExpenseStack.peek().getExpense();
        }
        System.out.println("全局最优总："+ooo);
    }

    /**
     * 通过栈排序,对车辆最优停车场排序
     * @param stack
     *          将要排序的栈记为stack，申请的辅助栈记为help。在stack上执行pop操作，弹出的元素记为cur.
     *               (1)果cur小于或等于help的栈顶元素，则将cur直接压入help；
     *               (2)如果cur大禹help的栈顶元素，则将该help的元素注意弹出，逐一压入stack栈，直到cur小鱼或等于help的栈顶元素，再将 cur压入help。
     */
    public static void sortStackByStack(Stack<CarToParkExpense> stack){
        Stack<CarToParkExpense> help = new Stack<>();
        while(!stack.isEmpty()){
            CarToParkExpense cur = stack.pop();
            while (!help.isEmpty()&&help.peek().getExpense()>cur.getExpense()){
                stack.push(help.pop());
            }
            help.push(cur);
        }
        while (!help.isEmpty()){
            stack.push(help.pop());
        }
    }

    /**
     *  计算梯度，同时计算车主i的停车场选择栈
     * @param stack
     */
    public static void selectStackByStack(Stack<CarToParkExpense> stack){
        Stack<CarToParkExpense> helpSel = new Stack<>();
        CarToParkExpense carToParkExpenseHelp;
        carToParkExpenseHelp = stack.pop();
        helpSel.push(carToParkExpenseHelp);
        while (!stack.isEmpty() && stack.peek().getExpense()-helpSel.peek().getExpense()<2.0 && stack.peek().getExpense()-carToParkExpenseHelp.getExpense()<4.0){
            helpSel.push(stack.pop());
        }
        while (!stack.isEmpty()){
            stack.pop();
        }
        while (!helpSel.isEmpty()){
            stack.push(helpSel.pop());
        }
    }

    /**
     * 随机选择栈的某一个元素放到栈顶，其余元素排序不变
     * @param stack
     * @return
     */
    public static int randomSortStackByStack(Stack<CarToParkExpense> stack){
        int randomTemp = 1+(int)(Math.random()*(stack.size()-1) +0.5f);
        Stack<CarToParkExpense> help = new Stack<>();
        CarToParkExpense carToParkExpense;
        while(randomTemp>1){
            help.push(stack.pop());
            randomTemp--;
        }
        carToParkExpense = stack.pop();
        while (!help.isEmpty()){
            stack.push(help.pop());
        }
        stack.push(carToParkExpense);
        return randomTemp;
    }

    /**
     * 利用temp恢复Stack的排序
     * @param temp
     * @param stack
     */
    public static void recoverStackBytemp(int temp, Stack<CarToParkExpense> stack){
        Stack<CarToParkExpense> help = new Stack<>();
        CarToParkExpense carToParkExpense;
        while(temp>1){
            help.push(stack.pop());
        }
        carToParkExpense = stack.pop();
        while (!help.isEmpty()){
            stack.push(help.pop());
        }
        stack.push(carToParkExpense);
    }

    /**
     * 比较两个矩阵的差合度,x和y的长度需要相同
     * @param x 对应GrTemp
     * @param y 对应GrDis
     *          如果GrDis优于Grtemp,
     */
    public static int compareArray(int[] x,int[] y){
        int sum = 0;
        for(int i=0;i<x.length;i++){
            sum = Math.abs(x[i]-y[i])+sum;
        }
        return sum;
    }

    /**
     * 根据输入的ArrayList来获取当前分配数组
     *
     */
    public static int[] getArrayByArrayList(int[] helpArray,ArrayList<CarToPark> carToParkArrayList){
        for(int i=0;i<carToParkArrayList.size();i++){
            switch (carToParkArrayList.get(i).getCarToParkExpenseStack().peek().getParkingLotName()){
                case "A世界":
                    helpArray[0]++;
                    break;
                case "省体育馆":
                    helpArray[1]++;
                    break;
                case "来福士":
                    helpArray[2]++;
                    break;
                case "文物院":
                    helpArray[3]++;
                    break;
                case "大陆国际":
                    helpArray[4]++;
                    break;
                case"华宇国府":
                    helpArray[5]++;
                    break;
            }
        }
        return helpArray;
    }

    /**
     *
     * @param X_User
     * @param Gr_Leader
     * @return 栈：包含用户最优大于系统分配的列
     */
    public static Stack<Integer> selectColumn(int[][] X_User,int[] Gr_Leader){
        Stack<Integer> stack = new Stack<>();
        int temp=0;
        for (int i=0;i<6;i++){
            for (int j=0;j<300;j++){
                if(X_User[j][i]==1){
                    temp++;
                }
            }
            if ((temp*10)>(Gr_Leader[i]*11)){
                stack.push(i);
            }
            temp = 0;
        }
        return stack;
    }

//    /**
//     * 首先需要将order中的记录以及time_in,time_out时间记录到orderHistory中，再删除order中的记录。
//     * @param username
//     * @param license
//     * @throws Exception
//     */
//    @PostMapping("/createHis")
//    public void saveOrderHis(@RequestParam("username") String username,
//                                @RequestParam("license") String license) throws Exception{
//        OrderReDO orderReDO = orderService.queryReOrder(username,license);
//        Date time_re = orderReDO.getTime_re();
//        Date time_out = new Date("yyyy-MM-dd HH:mm:ss");
//    }
//
//    @PostMapping("quaryhistory")
//    public List<StateDO> queryHisOrder(){
//        return stateDao.queryAll("swpu");
//    }
}
