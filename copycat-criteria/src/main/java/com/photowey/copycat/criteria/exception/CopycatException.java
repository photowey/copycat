package com.photowey.copycat.criteria.exception;

/**
 * 自定义异常类
 *
 * @author WcJun
 * @date 2019/05/12
 */
public final class CopycatException extends RuntimeException {

    private static final long serialVersionUID = -8570055408880880155L;

    /**
     * 无参构造器
     */
    public CopycatException() {
        super();
    }

    /**
     * 有参构造器
     *
     * @param message 抛出该异常是的信息
     */
    public CopycatException(String message) {
        super(message);
    }

    public CopycatException(String message, Object... params) {
        super(String.format(message, params));
    }

    /**
     * 有参构造器
     *
     * @param message 抛出该异常是的信息
     * @param cause   抛出该异常是的原因
     */
    public CopycatException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 有参构造器
     *
     * @param cause 抛出该异常是的原因
     * @auhtor WcJun
     */
    public CopycatException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message            抛出该异常是的信息
     * @param cause              抛出该异常是的原因
     * @param enableSuppression  布尔值:可否被抑制
     * @param writableStackTrace 布尔值:可否打堆栈信息跟踪
     */
    public CopycatException(String message, Throwable cause,
                            boolean enableSuppression,
                            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
