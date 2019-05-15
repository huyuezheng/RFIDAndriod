package com.zhl.scanlable.base;


/**
 * Created by dq on 2018/8/15 0015.
 */

public class Constant {
//    public static String URL = "http://111.23.244.165:8081/";

    public static String save_assetsmanagement = "Gspstore/watson/save_assetsmanagement.action";
    public static String queryAssetsManagement = "Gspstore/watson/queryAssetsManagement.action";
    public static String query_assets_type = "Gspstore/watson/query_assets_type.action";


    public static String check_assets_management_new = "Gspstore/watson/check_assets_management_new.action";//signalSources=111111111查询
    public static String save_assetsmanagement_new = "Gspstore/watson/save_assetsmanagement_new.action";//barcode=SN-E-0098&signalSource=111111111  激活
    public static String add_stock_check_entity = "Gspstore/watson/add_stock_check_entity.action";//stockCheckEntity=[{"barcode":"SN-E-0098","realnum":"1"}]  盘点
    public static String query_check_article = "Gspstore/watson/query_check_article.action";//serial=20181229102810  盘点查询
    public static String all = "rfid/all";//仓库查询
    public static String allDepartment = "/rfid/allDepartment";//部门查询
    public static String allSaleShop = "rfid/allSaleShop";//零售店查询
    public static String allGoodsType = "rfid/allGoodsType";//获取所有的商品类型信息
    public static String getGoodsByRfid = "rfid/getGoodsByRfid";//根据RFID获取商品的详细信息
    public static String getGoodsByRfidAndgoodsTypeIdAndDepartmentId = "rfid/getGoodsByRfidAndgoodsTypeIdAndDepartmentId";//根据RFID获取商品的详细信息
    public static String getGoodsByRfidAndgoodsTypeIdAndSaleShopId = "rfid/getGoodsByRfidAndgoodsTypeIdAndSaleShopId";//根据RFID获取商品的详细信息
    public static String bind = "rfid/bind";//将商品一维码和RFID码进行绑定
    public static String getGoodsCount = "rfid/getGoodsCount";//将商品一维码和RFID码进行绑定
    public static String getGoodsCountDepartment = "rfid/getGoodsCountDepartment";//根据部门id获取数量
    public static String getGoodsCountSaleShop = "rfid/getGoodsCountSaleShop";//根据零售店id获取数量
    public static String commitCheckInfo = "rfid/commitCheckInfo";//将商品一维码和RFID码进行绑定
}
