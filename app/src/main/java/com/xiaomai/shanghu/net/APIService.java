package com.xiaomai.shanghu.net;


import com.xiaomai.shanghu.bean.AppUpdateBean;
import com.xiaomai.shanghu.bean.DepositsBean;
import com.xiaomai.shanghu.bean.DeviceFreezeListBean;
import com.xiaomai.shanghu.bean.GetBalanceBean;
import com.xiaomai.shanghu.bean.GetCodeBean;
import com.xiaomai.shanghu.bean.GetCreditCardBean;
import com.xiaomai.shanghu.bean.GetDeviceDetailBean;
import com.xiaomai.shanghu.bean.GetDeviceFreezeDetailBean;
import com.xiaomai.shanghu.bean.GetDviceCountBean;
import com.xiaomai.shanghu.bean.GetFreezeCountBean;
import com.xiaomai.shanghu.bean.GetIndexBean;
import com.xiaomai.shanghu.bean.GetMessageBean;
import com.xiaomai.shanghu.bean.GetPageSellerBillBean;
import com.xiaomai.shanghu.bean.IndexBean;
import com.xiaomai.shanghu.bean.OrderListBean;
import com.xiaomai.shanghu.bean.PageDeviceListBean;
import com.xiaomai.shanghu.bean.RefeshTokenBean;
import com.xiaomai.shanghu.bean.UserLoginBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
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
    @GET("agentCenter/account/version/update")
    Observable<AppUpdateBean> appUpdata(@Query("type") String type);

}
