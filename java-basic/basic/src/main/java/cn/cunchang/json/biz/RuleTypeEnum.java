package cn.cunchang.json.biz;
/**
 *
 * @author kaisui
 * @date 2022/11/24
 **/
public enum RuleTypeEnum {
    /**
     *
     */
    OVER_DUE("overdue", "超期"),
    /**
     *
     */
    OVER_RATIO("overratio", "超框"),

    ;
    private String code;
    private String desc;

    RuleTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
    }
