package com.ccclubs.test;

import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSONObject;

public class SqlBiulder {

    public static void main(String[] args) {
        
        JSONObject json = new JSONObject();
        json.put("hours", 1);
        json.put("minutes", 60);
        System.out.println(json.getString("minutes"));
        
        json.clear();
        System.out.println(json.getString("minutes"));
        
//        getRentPriceSql(1, 1, 1);
    }

    public static void getRentPriceSql(long outletsId, long modelId, long userType) {
        String sql =
                "select cp.*, cr.csr_expression, cr.csr_id, cr.csr_name, cr.csr_priority, cg.csg_name, cg.csg_product from cs_price cp "
                        + " left join cs_goods cg on cp.csp_goods=cg.csg_id "
                        + " left join cs_rule cr on cr.csr_id = cg.csg_rule "
                        + " where cr.csr_status=1 and cr.csr_name !=\"每公里\" and cp.csp_outets=" + outletsId
                        + " and cp.csp_model=" + modelId + " and cp.csp_user_type=" + userType
                        + " and cg.csg_status=1  order by cr.csr_priority desc";
        System.out.println(sql);
    }

    public static void getDefaultRentPriceSql(long defaultOutletsId, long modelId, long userType) {
        String sql =
                "select cp.*, cr.csr_expression, cr.csr_id, cr.csr_name, cr.csr_priority, cg.csg_name, cg.csg_product from cs_price cp "
                        + " left join cs_goods cg on cp.csp_goods=cg.csg_id "
                        + " left join cs_rule cr on cr.csr_id = cg.csg_rule "
                        + " where cr.csr_status=1 and cr.csr_name !=\"每公里\" and cp.csp_outets=" + defaultOutletsId
                        + " and cp.csp_model=" + modelId + " and cp.csp_user_type=" + userType
                        + " and cg.csg_status=1  order by cr.csr_priority desc";
        System.out.println(sql);
    }
}
