package io.github.dingfeiyang.context;


import io.github.dingfeiyang.PbManager;
import io.github.dingfeiyang.application.PbApplication;
import io.github.dingfeiyang.context.model.PbRequest;
import io.github.dingfeiyang.context.model.PbResponse;
import io.github.dingfeiyang.context.model.PbStorage;

/**
 * 上下文持有类，你可以通过此类快速获取当前环境下的 PbRequest、PbResponse、PbStorage、PbApplication 对象。
 */
public class PbHolder {

    /**
     * 获取当前请求的 PbContext 上下文对象
     *
     * @return /
     * @see PbContext
     */
    public static PbContext getContext() {
        return PbManager.getPbContext();
    }

    /**
     * 获取当前请求的 Request 包装对象
     *
     * @return /
     * @see PbRequest
     */
    public static PbRequest getRequest() {
        return PbManager.getPbContext().getRequest();
    }

    /**
     * 获取当前请求的 Response 包装对象
     *
     * @return /
     * @see PbResponse
     */
    public static PbResponse getResponse() {
        return PbManager.getPbContext().getResponse();
    }

    /**
     * 获取当前请求的 Storage 包装对象
     *
     * @return /
     * @see PbStorage
     */
    public static PbStorage getStorage() {
        return PbManager.getPbContext().getStorage();
    }

    /**
     * 获取全局 PbApplication 对象
     *
     * @return /
     * @see PbApplication
     */
    public static PbApplication getApplication() {
        return PbApplication.defaultInstance;
    }

}
