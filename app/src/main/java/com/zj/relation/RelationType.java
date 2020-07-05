package com.zj.relation;

public class RelationType
{
    /*基础关系类型：二进制000表示无关系，111表示父亲，110表示母亲，101表示夫妻，100表示儿子，011表示女儿*/
    public static final int NO_REALTION = 0;
    public static final int FATHER = (1 << 3) - 1;
    public static final int MOTHER = (1 << 3) - 2;
    public static final int COUPLE = (1 << 3) - 3;
    public static final int SON = (1 << 3) - 4;
    public static final int DAUGHTER = (1 << 3) - 5;

    /**
    *整体关系类型：通过每3位表示一个基础关系，共可表示9个关系链，空3位000用于表示结束，
    * 例如：A到B的关系为二进制00000000000000000000000111111100
    * 可如下分解00000000000000000000 000 111 111 100
    * 000表示结束位，111表示父亲，100表示儿子，因此A的父亲的父亲的儿子为B，亲属关系上即为伯或叔，
    * （叔或伯/姐或妹/哥或弟/妻子或丈夫）如果需要区分，可以用最高位1或0来区分（未实现），
     * 也可通过{@link RelationBean 中residentList的Resident的年龄/性别来判断}
    */

    /*弟弟/哥哥*/
    public static final int FATHER_SON = (FATHER << 3) + SON;
    /*哥哥*/
    //public static final int FATHER_SON_OLDER = FATHER_SON_YOUNGER | (1 << 24);
    /*妹妹/姐姐*/
    public static final int FATHER_DAUGHTER = (FATHER << 3) + DAUGHTER;
    /*姐姐*/
    //public static final int FATHER_DAUGHTER_OLDER = FATHER_DAUGHTER_YOUNGER | (1 << 24);
    /*祖父*/
    public static final int FATHER_FATHER = (FATHER << 3) + FATHER;
    /*叔/伯*/
    public static final int FATHER_FATHER_SON = (FATHER_FATHER << 3) + SON;
    /*伯*/
    //public static final int FATHER_FATHER_SON_OLDER = FATHER_FATHER_SON_YOUNGER | (1 << 24);
    /*堂弟/堂兄*/
    public static final int FATHER_FATHER_SON_SON = (FATHER_FATHER_SON << 3) + SON;
    /*堂兄*/
    //public static final int FATHER_FATHER_SON_SON_OLDER = FATHER_FATHER_SON_SON_YOUNGER | (1 << 24);
    /*外祖父*/
    public static final int MOTHER_FATHER = (MOTHER << 3) + FATHER;
    /*外祖母*/
    public static final int MOTHER_MOTHER = (MOTHER << 3) + MOTHER;
    /*舅*/
    public static final int MOTHER_FATHER_SON = (MOTHER_FATHER << 3) + SON;
    /*姨*/
    public static final int MOTHER_FATHER_DAUGHTER = (MOTHER_FATHER << 3) + DAUGHTER;
    /*舅表兄/弟*/
    public static final int MOTHER_FATHER_SON_SON = (MOTHER_FATHER_SON << 3) + SON;
    /*舅表姐/妹*/
    public static final int MOTHER_FATHER_SON_DAUGHTER = (MOTHER_FATHER_SON << 3) + DAUGHTER;
    /*姨表兄/弟*/
    public static final int MOTHER_FATHER_DAUGHTER_SON = (MOTHER_FATHER_DAUGHTER << 3) + SON;
    /*姨表姐/妹*/
    public static final int MOTHER_FATHER_DAUGHTER_DAUGHTER = (MOTHER_FATHER_DAUGHTER << 3) + DAUGHTER;
    /*...略...*/
}
