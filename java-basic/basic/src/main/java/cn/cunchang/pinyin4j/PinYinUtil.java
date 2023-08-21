package cn.cunchang.pinyin4j;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import static net.sourceforge.pinyin4j.format.HanyuPinyinCaseType.LOWERCASE;
import static net.sourceforge.pinyin4j.format.HanyuPinyinToneType.WITHOUT_TONE;
import static net.sourceforge.pinyin4j.format.HanyuPinyinToneType.WITH_TONE_NUMBER;
import static net.sourceforge.pinyin4j.format.HanyuPinyinVCharType.WITH_V;

/**
 * @author lastwhisper
 */
public class PinYinUtil {

    /**
     * @param words 汉字字符串
     */
    public static String words2Pinyin(String words) {
        return words2Pinyin(words, getDefaultPinyinFormat());
    }

    /**
     * @param words 汉字字符串
     * @param pinyinFormat 汉字转拼音格式化模式
     */
    public static String words2Pinyin(String words, HanyuPinyinOutputFormat pinyinFormat) {
        return words2Pinyin(words.toCharArray(), pinyinFormat);
    }

    /**
     * @param chars 汉字字符数组
     * @param pinyinFormat 汉字转拼音格式化模式
     */
    public static String words2Pinyin(char[] chars, HanyuPinyinOutputFormat pinyinFormat) {
        StringBuilder pinyinBuilder = new StringBuilder();
        try {
            for (char word : chars) {
                //是否为汉字字符
                if (Character.toString(word).matches("[\\u4E00-\\u9FA5]+")) {
                    // 多音字
                    String[] py = PinyinHelper.toHanyuPinyinStringArray(word, pinyinFormat);
                    pinyinBuilder.append(py[0]);
                } else {
                    pinyinBuilder.append(word);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return pinyinBuilder.toString();
    }

    /**
     * 小写、音标无声调无音符、v表示ü
     * @return net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat
     */
    public static HanyuPinyinOutputFormat getDefaultPinyinFormat() {
        return getPinyinFormat(LOWERCASE, WITHOUT_TONE, WITH_V);
    }

    /**
     * 小写、音标有声调无音符、v表示ü
     * @return net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat
     */
    public static HanyuPinyinOutputFormat getToneNumberPinyinFormat() {
        return getPinyinFormat(LOWERCASE, WITH_TONE_NUMBER, WITH_V);
    }

    /**
     * 获取汉语拼音格式化器
     * @param caseType 大小写
     *        @see HanyuPinyinCaseType
     *         UPPERCASE：大写  (ZHONG)
     *         LOWERCASE：小写  (zhong)
     * @param toneType 音标格式
     *        @see HanyuPinyinToneType
     *         WITHOUT_TONE：无音标  (zhong)
     *         WITH_TONE_NUMBER：1-4数字表示英标  (zhong4)
     *         WITH_TONE_MARK：直接用音标符（必须WITH_U_UNICODE否则异常）  (zhòng)
     * @param charType charType
     *        @see HanyuPinyinVCharType
     *         WITH_V：用v表示ü  (nv)
     *         WITH_U_AND_COLON：用"u:"表示ü  (nu:)
     *         WITH_U_UNICODE：直接用ü (nü)
     * @return net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat
     */
    public static HanyuPinyinOutputFormat getPinyinFormat(HanyuPinyinCaseType caseType, HanyuPinyinToneType toneType, HanyuPinyinVCharType charType) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(caseType);
        format.setToneType(toneType);
        format.setVCharType(charType);
        return format;
    }

    /**
     * 汉字串转声调
     * @param words 字符串
     * @return java.lang.String 声调串
     *  1,2,3,4
     */
    public static String word2ToneNumber(String words) {
        String tones = words2Pinyin(words, getToneNumberPinyinFormat());
        StringBuilder toneNumBuilder = new StringBuilder();
        for (int i = 0; i < tones.length(); i++) {
            char c = tones.charAt(i);
            if (isTonal(c)) {
                toneNumBuilder.append(c);
            }
        }
        return toneNumBuilder.toString();
    }

    private static boolean isTonal(char c) {
        return isLow(c) || isHeight(c);
    }

    private static boolean isLow(char c) {
        return c == 49 || c == 50;
    }

    private static boolean isHeight(char c) {
        return c == 51 || c == 52;
    }

    /**
     * 汉字串转平仄
     * @param words 字符串
     * @return java.lang.String 平仄
     *  1,2：平、3,4：仄
     *  平：0、仄：1
     */
    public static String word2Tonal(String words) {
        String tones = words2Pinyin(words, getToneNumberPinyinFormat());
        StringBuilder tonalNumBuilder = new StringBuilder();
        for (int i = 0; i < tones.length(); i++) {
            char c = tones.charAt(i);
            if (isLow(c)) {
                tonalNumBuilder.append(0);
            } else if (isHeight(c)) {
                tonalNumBuilder.append(1);
            }
        }
        return tonalNumBuilder.toString();
    }

    /**
     * @param binary 二进制串
     * @return java.lang.String 对应的ASCII
     */
    public static String binary2Ascii(String binary) {
        StringBuilder asciiBuilder = new StringBuilder();
        for (int i = 0; i < binary.length(); i += 8) {
            asciiBuilder.append((char) Integer.parseInt(binary.substring(i, i + 8), 2));
        }
        return asciiBuilder.toString();
    }

    public static void main(String[] args) {
        String words = "原谅女儿离开父亲 昨日看到急报提示 ";
        String tonal = word2Tonal(words);
        System.out.println(tonal);
        String str = binary2Ascii(tonal);
        System.out.println(str);
    }

}