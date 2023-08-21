package cn.cunchang.json;

public enum FeatureComment {

    /**
     * 这个特性，决定了解析器是否将自动关闭那些不属于parser自己的输入源。 如果禁止，则调用应用不得不分别去关闭那些被用来创建parser的基础输入流InputStream和reader；如果允许，parser只要自己需要获取closed方法（当遇到输入流结束，或者parser自己调用 JsonParder#close方法），就会处理流关闭。
     * 注意：这个属性默认是true，即允许自动关闭流
     */
    AutoCloseSource,

    /**
     * 该特性决定parser将是否允许解析使用Java/C++ 样式的注释（包括'/'+'*' 和'//' 变量）。 由于JSON标准说明书上面没有提到注释是否是合法的组成，所以这是一个非标准的特性；尽管如此，这个特性还是被广泛地使用。
     * 注意：该属性默认是false，因此必须显式允许，即通过JsonParser.Feature.ALLOW_COMMENTS 配置为true。
     */
    AllowComment,

    /**
     * 这个特性决定parser是否将允许使用非双引号属性名字， （这种形式在Javascript中被允许，但是JSON标准说明书中没有）。
     * 注意：由于JSON标准上需要为属性名称使用双引号，所以这也是一个非标准特性，默认是false的。
     * 同样，需要设置JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES为true，打开该特性。
     */
    AllowUnQuotedFieldNames,

    /**
     * 该特性决定parser是否允许单引号来包住属性名称和字符串值。
     * 注意：默认下，该属性也是关闭的。需要设置JsonParser.Feature.ALLOW_SINGLE_QUOTES为true
     */
    AllowSingleQuotes,

    /**
     * 该特性决定JSON对象属性名称是否可以被String#intern 规范化表示。如果允许，则JSON所有的属性名将会 intern() ；如果不设置，则不会规范化，默认下，该属性是开放的。此外，必须设置CANONICALIZE_FIELD_NAMES为true
     * 关于intern方法作用：当调用 intern 方法时，如果池已经包含一个等于此 String 对象的字符串 （该对象由 equals(Object) 方法确定），则返回池中的字符串。否则，将此 String
     * 对象添加到池中， 并且返回此 String 对象的引用。
     */
    InternFieldNames,

    //这个设置为true则遇到字符串符合ISO8601格式的日期时，会直接转换成日期类。
    AllowISO8601DateFormat,

    //允许多重逗号,如果设为true,则遇到多个逗号会直接跳过;
    AllowArbitraryCommas,

    //这个设置为true则用BigDecimal类来装载数字，否则用的是double；
    UseBigDecimal,

    //忽略不匹配
    IgnoreNotMatch,

    //如果你用fastjson序列化的文本，输出的结果是按照fieldName排序输出的，parser时也能利用这个顺序进行优化读取。这种情况下，parser能够获得非常好的性能
    SortFeidFastMatch,

    //禁用ASM
    DisableASM,

    //禁用循环引用检测
    DisableCircularReferenceDetect,

    //对于没有值得字符串属性设置为空串
    InitStringFieldAsEmpty,

    //支持数组to对象
    SupportArrayToBean,

    //属性保持原来的顺序
    OrderedField,

    //禁用特殊字符检查
    DisableSpecialKeyDetect,

    //使用对象数组
    UseObjectArray,


    ;


}
