import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fadada.sdk.client.FddClientBase;
import com.fadada.sdk.client.FddClientExtra;
import com.fadada.sdk.client.authForfadada.*;
import com.fadada.sdk.client.authForfadada.model.AgentInfoINO;
import com.fadada.sdk.client.authForfadada.model.BankInfoINO;
import com.fadada.sdk.client.authForfadada.model.CompanyInfoINO;
import com.fadada.sdk.client.authForfadada.model.LegalInfoINO;
import com.fadada.sdk.client.request.ExtsignReq;
import com.fadada.sdk.test.util.ConfigUtil;
import com.fadada.sdk.util.crypt.FddEncryptTool;
import com.fadada.sdk.util.http.HttpsUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class auth4fadada {
    private static String APP_ID = "";
    private static String APP_SECRET = "";
    private static String V = "2.0";
    private static String HOST = "https://testapi.fadada.com:8443/api/";

    private String customer_name;
    private String id_card = "";// 身份证号码;
    private String ident_type = "";// 身份证类型 ;
    private String mobile;
    private String contract_id;
    private String template_id;
    private String transaction_id;
    private String email;
    private File file;
    private StringBuffer response = new StringBuffer("==================Welcome ^_^ ==================");

    @Before
    public void before() {
        String timeStamp = HttpsUtil.getTimeStamp();

        // customer_name = ConfigUtil.getPersonName();
        customer_name = "章熙祥";
        contract_id = "CONT" + timeStamp;// 直接上传接口合同编号
        template_id = "TEMP" + timeStamp;// 模板编号
        transaction_id = "TRAN_" + timeStamp;// 手动签交易号
        email = "ep" + timeStamp + "@fadada.com";// 个人邮箱
        id_card = "";// 身份证号码;
        mobile = "";// 手机号;
        // file = new File(ConfigUtil.getFilePath());
        file = new File("/Users/didi1/Documents/房屋租赁合同-可填充.pdf");

        response.append("\n").append("名字:").append(customer_name);
        response.append("\n").append("身份证号码:").append(id_card);
        response.append("\n").append("证件类型:").append(ident_type);
        response.append("\n").append("手机号:").append(mobile);
        response.append("\n").append("邮箱:").append(email);
        response.append("\n").append("上传合同编号:").append(contract_id);
        response.append("\n").append("上传模板编号:").append(template_id);
        response.append("\n").append("手动签交易号:").append(transaction_id);
    }

    /*=============法大大实名测试=============*/
    @Test
    public void Test1() {
        // checked
//         regAccount();//1注册账号
//         getAuthCompanyurl();//2获取企业实名认证地址
        // checked
        getAuthPersonurl();// 3获取个人实名认证地址

        // checked
//         findPersonCertInfo();// 17 查询个人实名认证信息
        // findCompanyCertInfo();//18 查询企业实名认证信息
        // checked
//         GetFileForUUID();//19 通过uuid下载文件
//         ApplyCert();// 4实名证书申请
        // ApplyNumCert();//5编号证书申请
        // addSignature1();//6印章上传
        // addSignature2();//6印章上传
        // customSignature();//7自定义印章

    }

    @Test
    public void Test2() {
        // uploadContract();// 8合同上传
        // uploadTemplate();// 9模板上传
        GenerateContract();// 10模板填充
    }

    @Test
    public void Test3() {
        // uploadContract();// 8合同上传
        extsign();// 12手动签署接口
        // extsignAuto();// 11自动签署
        // viewContract();// 13合同查看
        // downloadContract();// 14合同下载
        // contractFiling();// 15合同归档
    }

    /*=============注册账号=============*/
    public void regAccount() {
        response.append("\n").append("注册账号:");
        FddClientBase base = new FddClientBase(APP_ID, APP_SECRET, V, HOST);
        String open_id = transaction_id;
        // 个人
        String account_type = "1";
        // 企业
//         String account_type = "2";
        String result = base.invokeregisterAccount(open_id, account_type);
        response.append("\n").append(result);
    }

    /*=============获取企业实名认证地址=============*/
    public void getAuthCompanyurl() {
        response.append("\n").append("获取企业实名认证地址:");
        GetCompanyVerifyUrl comverify = new GetCompanyVerifyUrl(APP_ID, APP_SECRET, V, HOST);
        CompanyInfoINO companyInfo = new CompanyInfoINO();
        companyInfo.setCompany_name("");
        companyInfo.setCredit_no("");
        companyInfo.setCredit_image_path("");

        BankInfoINO bankInfo = new BankInfoINO();
        bankInfo.setBank_name("");
        bankInfo.setBank_id("");
        bankInfo.setSubbranch_name("");

        LegalInfoINO legalInfo = new LegalInfoINO();
        legalInfo.setLegal_name("");
        legalInfo.setLegal_id("");
        legalInfo.setLegal_mobile("");
        legalInfo.setLegal_id_front_path("");

        AgentInfoINO agentInfo = new AgentInfoINO();
        agentInfo.setAgent_name("");
        agentInfo.setAgent_id("");
        agentInfo.setAgent_mobile("");
        agentInfo.setAgent_id_front_path("");
        // String customer_id = "672FA01EC878E631A506F4CEF44FD2DC";// 必填
//        String customer_id = "2BBD285385D605921944E5F2EB6F3C46";// 必填 四川
        String customer_id = "16EBADEB81780EC7737509022A59A04D";// 必填 海南

        /**
         * 实名认证套餐类型 0：标准方案（对公打款+纸质审核）默认0； 1：对公打款； 2：纸质审核',
         */
        String verifyed_way = "2";
        String page_modify = "1";// 必填
        String m_verified_way = "0";// 实名认证套餐类型

        String company_principal_type = "1";// 1.法人，2代理人
        String notify_url = "http://www.baidu.com";// 必填
        String return_url = "http://www.baidu.com";// 可填
        String result = comverify.invokeCompanyVerifyUrl(companyInfo, bankInfo, legalInfo, agentInfo, customer_id,
            verifyed_way, m_verified_way, page_modify, company_principal_type, return_url, notify_url, "", "", "", "",
            "", "", "");
        response.append("\n").append(result);
        String data = JSON.parseObject(result).getString("data");
        if (null != data) {
            String url = JSON.parseObject(data).getString("url");
            url = decode(url);
            System.out.println(url);
        }
    }

    /*=============获取个人实名认证地址=============*/
    public void getAuthPersonurl() {

        response.append("\n").append("获取个人实名认证地址:");
        GetPersonVerifyUrl personverify = new GetPersonVerifyUrl(APP_ID, APP_SECRET, V, HOST);
        // 熙祥1 实名后 使用紫钲实名
//        String customer_id = "FB893A190B0FDBE1BF2694C972013CA0";
        // String customer_id = "6152A5E4D7EA840FEA15864EBA2CF8E2";
        // 紫钲
//         String customer_id = "FD40CE205A1969D1DDAD761C6AED39E6";
        // 熙祥2
        // String customer_id = "D5D4DC0C510BB0D0020C900A85E76CFA";
        // 熙祥3
        // String customer_id = "927E3A66B85189B721E0F37446DAF852";
        String customer_id = "99EAEBA8CB8F4B956FADDDA07A96895B";// 测试获取实名地址时传入身份证号码做身份校验

        String verifyed_way = "9";
        String page_modify = "2";
        String notify_url = "https://www.baidu.com";
        String return_url = "https://www.baidu.com";
        String customer_name = "";
        String customer_ident_type = "0";
        String customer_ident_no = "";
        String mobile = "";
        String ident_front_path = "";
//        自动申请证书
        String certFlag = "1";
        String result = personverify.invokePersonVerifyUrl(customer_id, verifyed_way, page_modify, notify_url,
            return_url, customer_name, customer_ident_type, customer_ident_no, mobile, ident_front_path, "", certFlag,
            "", "", "", "", "");
        response.append("\n").append(result);
        String data = JSON.parseObject(result).getString("data");
        if (null != data) {
            String url = JSON.parseObject(data).getString("url");
            url = decode(url);
            System.out.println(url);
        }
    }

    /*=============查询个人实名认证信息=============*/
    public void findPersonCertInfo() {
        response.append("\n").append("查询个人实名认证信息:");
        FindCertInfo personCertInfo = new FindCertInfo(APP_ID, APP_SECRET, V, HOST);
//         String verified_serialno = "404983c8c866415aa004769441023e15";
        // zz
        String verified_serialno = "e0c2e221bc7244d2b16eab76f93c0f8c";
        // xx2
        // String verified_serialno = "1172c92ad90b4b12bcf7a6ec1dfbf858";
        String result = personCertInfo.invokeFindPersonCert(verified_serialno, "1");
        response.append("\n").append(result);
    }

    /*=============查询企业实名认证信息=============*/
    public void findCompanyCertInfo() {
        response.append("\n").append("查询企业实名认证信息:");
        FindCertInfo personCertInfo = new FindCertInfo(APP_ID, APP_SECRET, V, HOST);
        String verified_serialno = "e076a8d7745d4f83a07b95d9d7325215";
        String result = personCertInfo.invokeFindPersonCert(verified_serialno, "2");
        response.append("\n").append(result);
    }

    public void ApplyCert() {
        response.append("\n").append("申请实名证书:");
        ApplyCert applyCert = new ApplyCert(APP_ID, APP_SECRET, V, HOST);
        // 个人
        // String customer_id = "FB893A190B0FDBE1BF2694C972013CA0";
        // String verified_serialno = "404983c8c866415aa004769441023e15";
        // 公司
//        String customer_id = "672FA01EC878E631A506F4CEF44FD2DC";
//        String verified_serialno = "e076a8d7745d4f83a07b95d9d7325215";
//        zz
        String customer_id = "FD40CE205A1969D1DDAD761C6AED39E6";
        String verified_serialno = "e0c2e221bc7244d2b16eab76f93c0f8c";
        String result = applyCert.invokeApplyCert(customer_id, verified_serialno);
        response.append("\n").append(result);
    }

    public void ApplyNumCert() {
        response.append("\n").append("编号证书申请:");
        ApplyNumCert applyNumCert = new ApplyNumCert(APP_ID, APP_SECRET, V, HOST);
        String customer_id = "";
        String verified_serialno = "";
        String result = applyNumCert.invokeapplyNumcert(customer_id, verified_serialno);
        response.append("\n").append(result);
    }

    public void GetFileForUUID() {
        response.append("\n").append("通过uuid下载文件地址:");
        GetFileForUUID getFileForUUID = new GetFileForUUID(APP_ID, APP_SECRET, V, HOST);
//        xx
//        String uuid = "e92c505353c543678ddd160854b6be5e";
        // zz
        String uuid = "0d98821cf1ad47a8ad853610524362c6";
        String result = getFileForUUID.invokeFileForUUID(uuid, "");
        response.append("\n").append(result);
    }

    public void addSignature1() {
        response.append("\n").append("印章上传:");
        FddClientBase base = new FddClientBase(APP_ID, APP_SECRET, V, HOST);
        File imgfile = new File("/Users/didi/Documents/ziped上传测试公章PNG.png");

        String result = base.invokeaddSignature("672FA01EC878E631A506F4CEF44FD2DC", imgfile, "");
        response.append("\n").append(result);
    }

    public void addSignature2() {
        response.append("\n").append("印章上传:");
        FddClientBase base = new FddClientBase(APP_ID, APP_SECRET, V, HOST);
        String signature_img_base64 = "";
        String result = base.invokeaddSignature("", signature_img_base64);
        response.append("\n").append(result);
    }

    public void customSignature() {
        response.append("\n").append("自定义印章:");
        FddClientBase base = new FddClientBase(APP_ID, APP_SECRET, V, HOST);
        String customer_id = "";
        String content = "content";
        String result = base.invokecustomSignature(customer_id, content);
        response.append("\n").append(result);
    }

    public void uploadContract() {
        response.append("\n").append("上传合同接口");
        FddClientBase base = new FddClientBase(APP_ID, APP_SECRET, V, HOST);
        String result = base.invokeUploadDocs(contract_id, "合同标题", null, "", ".pdf");
        response.append("\n").append(result);
    }

    public void uploadTemplate() {
        response.append("\n").append("上传模板");
        FddClientBase base = new FddClientBase(APP_ID, APP_SECRET, V, HOST);
        String doc_url = null;
        String result = base.invokeUploadTemplate(template_id, file, doc_url);
        response.append("\n").append(result);
    }

    public void GenerateContract() {
        try {
            response.append("\n").append("合同生成");
            FddClientBase base = new FddClientBase(APP_ID, APP_SECRET, V, HOST);
            String font_size = "";
            String font_type = "";
            String paramter = "";
            paramter = getparamter();
            String dynamic_tables = "";
            // dynamic_tables = getdynamic_tables();
            dynamic_tables = "";
            // template_id = "TEMP20210623173953";
            String result = base.invokeGenerateContract("TEMP20210623173953", contract_id, "", font_size, font_type,
                paramter, dynamic_tables);
            response.append("\n").append(result);
            String viewpdf_url = JSON.parseObject(result).getString("viewpdf_url");
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + viewpdf_url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void extsign() {
        response.append("\n").append("手动签署接口:");
        FddClientBase base = new FddClientBase(APP_ID, APP_SECRET, V, HOST);
        ExtsignReq req = new ExtsignReq();
//        req.setCustomer_id("FB893A190B0FDBE1BF2694C972013CA0");
        // 紫钲
         req.setCustomer_id("FD40CE205A1969D1DDAD761C6AED39E6");
        // 熙祥2
        // req.setCustomer_id("D5D4DC0C510BB0D0020C900A85E76CFA");
        req.setTransaction_id(transaction_id);
        // req.setContract_id("CONT20210623174559");
        req.setContract_id("CONT20210624115805");
        req.setDoc_title("1");
        req.setReturn_url("");
        String result = base.invokeExtSign(req);
        response.append("\n").append(result);
    }

    public static void main(String[] args) throws Exception {
        requestAutoSign();
    }

    // 申请自动签署
    public static void requestAutoSign() throws Exception {
        // response.append("\n").append("申请手动签署接口:");
        // FddClientBase base = new FddClientBase(APP_ID, APP_SECRET, V, HOST);
        // ExtsignReq req = new ExtsignReq();
        // req.setCustomer_id("672FA01EC878E631A506F4CEF44FD2DC");
        // req.setTransaction_id(transaction_id);
        // req.setContract_id("CONT20210623174559");
        // req.setDoc_title("1");
        // req.setReturn_url("");
        // String result = "https://FDDServer:Port/api/before_authsign.api";
        // response.append("\n").append(result);

        Long timeStamp = 20210623180156L;

        // String sha1 = FddEncryptTool.sha1(APP_ID + FddEncryptTool.md5Digest("TRAN_20210623180156" + timeStamp)
        // + FddEncryptTool.sha1(APP_SECRET + "672FA01EC878E631A506F4CEF44FD2DC"));
        String sha1 = FddEncryptTool.sha1(APP_ID + FddEncryptTool.md5Digest("TRAN_20210623180157" + timeStamp)
            + FddEncryptTool.sha1(APP_SECRET + "672FA01EC878E631A506F4CEF44FD2DC"));
        String msgDigest = new String(FddEncryptTool.Base64Encode(sha1.getBytes()));
        System.out.println(msgDigest);
    }

    public void extsignAuto() {
        try {
            response.append("\n").append("自动签");
            FddClientBase base = new FddClientBase(APP_ID, APP_SECRET, V, HOST);
            ExtsignReq req = new ExtsignReq();
            req.setCustomer_id("672FA01EC878E631A506F4CEF44FD2DC");
            req.setTransaction_id(transaction_id);
            // req.setContract_id("CONT20210623174559");
            req.setContract_id("CONT20210626141618");
            req.setClient_role("1");
            req.setSign_keyword("");
            req.setDoc_title("1");
            req.setPosition_type("0");
            req.setSign_keyword("老王");
            String result = base.invokeExtSignAuto(req);
            response.append("\n").append(result);
            String viewpdf_url = JSON.parseObject(result).getString("viewpdf_url");
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + viewpdf_url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewContract() {
        try {
            response.append("\n").append("合同查看");
            FddClientExtra extra = new FddClientExtra(APP_ID, APP_SECRET, V, HOST);
            String contract_id = "CONT20210623174559";
            String result = extra.invokeViewPdfURL(contract_id);
            response.append("\n").append(result);
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void downloadContract() {
        response.append("\n").append("合同下载");
        String contract_id = "";
        FddClientExtra extra = new FddClientExtra(APP_ID, APP_SECRET, V, HOST);
        String result = extra.invokeDownloadPdf(contract_id);
        response.append("\n").append(result);
    }

    public void contractFiling() {
        response.append("\n").append("合同归档");
        FddClientBase base = new FddClientBase(APP_ID, APP_SECRET, V, HOST);
        String contract_id = "CONT20210623174559";
        String result = base.invokeContractFilling(contract_id);
        response.append("\n").append(result);
    }

    @After
    public void after() {
        System.out.println(response);
    }

    private String decode(String bizContent) {
        try {
            bizContent = URLDecoder.decode(bizContent, "utf-8");
            bizContent = new String(Base64.decodeBase64(bizContent.getBytes()));
        } catch (UnsupportedEncodingException e) {
            return "";
        }
        return bizContent;
    }

    private String getparamter() {
        JSONObject paramter = new JSONObject();
        // paramter.put("homeUrl","√");
        paramter.put("fill_4_2", "司机小明");
        paramter.put("fill_5_2", "承运商老王");
        return paramter.toString();
    }

    private String getdynamic_tables() {
        JSONArray dynamic_tables = new JSONArray();
        JSONObject dynamic2 = new JSONObject();
        dynamic2.put("insertWay", 1);
        // dynamic2.put("keyword", "");
        dynamic2.put("pageBegin", "1");
        dynamic2.put("cellHeight", "16.0");

        dynamic2.put("colWidthPercent", new int[] {3, 4, 4, 4});
        dynamic2.put("theFirstHeader", "附二");
        dynamic2.put("cellHorizontalAlignment", "1");
        dynamic2.put("cellVerticalAlignment", "5");
        dynamic2.put("headers", new String[] {"序号", "借款人", "贷款人", "金额"});
        String row1[] = new String[] {"1", "小网", "小易", "1000"};
        String row2[] = new String[] {"2", "小云", "小音", "2000"};
        String row3[] = new String[] {"3", "小乐", "天马", "3000"};
        dynamic2.put("datas", new String[][] {row1, row2, row3});
        dynamic2.put("headersAlignment", "1");
        dynamic2.put("tableWidthPercentage", 80);
        dynamic_tables.add(dynamic2);
        System.out.println(dynamic_tables.toString());
        return dynamic_tables.toString();
    }
}
