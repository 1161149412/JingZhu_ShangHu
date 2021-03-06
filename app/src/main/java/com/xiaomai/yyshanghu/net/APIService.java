package com.xiaomai.yyshanghu.net;


import com.xiaomai.yyshanghu.bean.AddVipBean;
import com.xiaomai.yyshanghu.bean.AppUpdateBean;
import com.xiaomai.yyshanghu.bean.DepositsBean;
import com.xiaomai.yyshanghu.bean.DeviceFreezeListBean;
import com.xiaomai.yyshanghu.bean.GetBalanceBean;
import com.xiaomai.yyshanghu.bean.GetCodeBean;
import com.xiaomai.yyshanghu.bean.GetCreditCardBean;
import com.xiaomai.yyshanghu.bean.GetDeviceDetailBean;
import com.xiaomai.yyshanghu.bean.GetDeviceFreezeDetailBean;
import com.xiaomai.yyshanghu.bean.GetDviceCountBean;
import com.xiaomai.yyshanghu.bean.GetFreezeCountBean;
import com.xiaomai.yyshanghu.bean.GetIndexBean;
import com.xiaomai.yyshanghu.bean.GetMessageBean;
import com.xiaomai.yyshanghu.bean.GetPageSellerBillBean;
import com.xiaomai.yyshanghu.bean.IndexBean;
import com.xiaomai.yyshanghu.bean.OrderListBean;
import com.xiaomai.yyshanghu.bean.PageDeviceListBean;
import com.xiaomai.yyshanghu.bean.RefeshTokenBean;
import com.xiaomai.yyshanghu.bean.UserLoginBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author azheng
 * @date 2018/4/24.
 * GitHub：https://github.com/RookieExaminer
 * Email：wei.azheng@foxmail.com
 * Description：
 */
public interface APIService {

    //TODO 获取验证码
    @GET("seller/message/captcha")
    Observable<GetCodeBean> getCode(@Query("mobile") String mobile);

    //TODO 登录
    @POST("seller/message/login")
    Observable<UserLoginBean> getUserInfo(@Body RequestBody requestBody);

    //TODO 测试验证码
    @GET("seller/message/captchaTest")
    Observable<GetCodeBean> getCaptchaTest(@Query("mobile") String mobile);

    //TODO 首页
    @GET("seller/account/index")
    Observable<IndexBean> getIndexBean();

    //TODO 获取各种类型设备数量
    @GET("seller/device/getDeviceCount")
    Observable<GetDviceCountBean> getDeviceCount();

    //TODO 查询冻结设备和金额
    @GET("seller/device/getFreezeCount")
    Observable<GetFreezeCountBean> getFreezeCount();

    //TODO 商户端账单Index
    @GET("seller/check/index")
    Observable<GetIndexBean> getIndex();

    //TODO 退出登录
    @GET("seller/message/loginOut")
    Observable<GetCodeBean> LoginOut();

//    //TODO 通过code登录
//    @GET("seller/message/loginByCode")
//    Observable<LoginByCodeBean> LoginByCode(@Body String code);

    //TODO app端刷新token操作
    @GET("seller/message/refreshToken")
    Observable<RefeshTokenBean> RefreshToken();

    //TODO 设备详情
    @GET("seller/device/{id}/deviceDetail")
    Observable<GetDeviceDetailBean> GetDeviceDetail(@Path("id") String id);

    //TODO 获取冻结设备详情
    @GET("seller/device/{id}/getDeviceFreezeDetail")
    Observable<GetDeviceFreezeDetailBean> GetDeviceFreezeDetail(@Path("id") String id);

    //TODO 提现记录,body传参
    @POST("seller/account/pageWithdrawRecord")
    Observable<DepositsBean> getDepositBean(@Body RequestBody requestBody);

    //TODO 分页获取冻结设备列表
    @POST("seller/device/getDeviceFreezeList")
    Observable<DeviceFreezeListBean> getDeviceFreezeList(@Body RequestBody requestBody);

    //TODO 分页获取设备列表
    @POST("seller/device/pageDeviceList")
    Observable<PageDeviceListBean> getpageDeviceList(@Body RequestBody requestBody);

    //TODO 订单查询
    @POST("seller/order/list")
    Observable<OrderListBean> getOrderList(@Body RequestBody requestBody);

    //TODO 获取商家账单
    @POST("seller/check/pageSellerBill")
    Observable<GetPageSellerBillBean> getpageSellerBill(@Body RequestBody requestBody);

    //TODO 发起提现申请
    @PUT("seller/account/addWithdraw")
    Observable<GetCodeBean> addWithdraw(@Body RequestBody requestBody);

    //TODO 获取商户个人信息
    @GET("seller/message/getMessage")
    Observable<GetMessageBean> getMessage();

    //TODO 修改商户信息
    @POST("seller/message/updateMessage")
    Observable<GetCodeBean> updateMessage(@Body RequestBody requestBody);

    //TODO 获取商家银行卡
    @GET("seller/account/getCreditCard")
    Observable<GetCreditCardBean> getCreditCard();

    //TODO 增加或修改商家银行卡
    @POST("seller/account/addOrUpdateCreditCard")
    Observable<GetCodeBean> addOrUpdateCreditCard(@Body RequestBody requestBody);

    //TODO 获取商家提现中金额和可提现金额
    @GET("seller/account/getBalance")
    Observable<GetBalanceBean> getBalance();

    //TODO APP更新
    @GET("agentCenter/account/version/update") //type  1：为代理端  2：为商户端
    Observable<AppUpdateBean> appUpdata(@Query("type") String type);

    //TODO 分页获取白名单列表
    @POST("seller/whiteList/pageWhiteList")
    Observable<AddVipBean> getPageWhiteList(@Body RequestBody requestBody);

    //TODO 新增商户白名单
    @POST("seller/whiteList/save")
    Observable<GetCodeBean> addSave(@Body RequestBody requestBody);

    //TODO 发送白名单添加的短信验证码
    @GET("seller/whiteList/sendCode")
    Observable<GetCodeBean> getSendCode(@Query("mobile") String mobile);

    //TODO 发送测试白名单短信验证码
    @GET("seller/whiteList/sendTestCode")
    Observable<GetCodeBean> sendTestCode(@Query("mobile") String mobile);

    //TODO 删除该条白名单
    @DELETE("seller/whiteList/{id}")
    Observable<GetCodeBean> delete(@Path("id") String id);
}
