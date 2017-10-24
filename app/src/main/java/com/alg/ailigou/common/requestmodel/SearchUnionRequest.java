package com.alg.ailigou.common.requestmodel;

import java.io.Serializable;

/**
 * Created by 海航
 * on 2017/8/4.
 * 此类或接口用于
 */

public class SearchUnionRequest implements Serializable {
    public  String unionName;//商家名称
    public  int typeId;//联盟商家分类id
    public  int areaId;//地区的id
    public int sort;//排序方式:0不排序，1 按照综合升序...  2,按照综合降序 3,按照销量升序 4,按照销量降序 5,按照价格升序 6 按照价格降序 7,按照人气升序 8 按照人气降序
    public int page;  //当前请求的页数
    public int pageSize;  //当前请求的大小
}
