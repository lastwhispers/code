package cn.cunchang.date;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.temporal.IsoFields;

/**
 * 聚合类型
 *
 * @author cunchang
 * @date 2020/11/4 17:10
 */
@Getter
@AllArgsConstructor
public enum AggregateTypeEnum {

    月(1, "月", 1),
    季(2, "季", 3),
    半年(3, "半年", 6),
    年(4, "年", 12),

    ;
    private Integer code;
    private String desc;
    /**
     * 步长
     */
    private Integer step;


    /**
     * 输出格式化日期
     *
     * @param localDate
     * @return
     */
    public String format(LocalDate localDate) {
        String topCategoryName = null;
        switch (this) {
            case 月:
                topCategoryName = localDate.getYear() + "年" + localDate.getMonthValue() + "月";
                break;
            case 季:
                int quarter = localDate.get(IsoFields.QUARTER_OF_YEAR);
                topCategoryName = localDate.getYear() + "年" + "Q" + quarter;
                break;
            case 半年:
                quarter = localDate.get(IsoFields.QUARTER_OF_YEAR);
                if (quarter <= 2) {
                    topCategoryName = localDate.getYear() + "年" + "上半年";
                }
                if (quarter >= 3) {
                    topCategoryName = localDate.getYear() + "年" + "下半年";
                }
                break;
            case 年:
                topCategoryName = localDate.getYear() + "年";
                break;
            default:

        }
        return topCategoryName;
    }
}