package com.alg.ailigou.common.requestmodel;

/**
 * AiligouApp
 * com.alg.ailigou.common.requestmodel
 * Created by Chris Chen on 2017/7/26 19:00.
 * Explain:
 */

public class SearchRequest {
    public String keyWords;//搜索关键字
    public int sort;//排序方式:0不排序，1 按照综合升序...  2,按照综合降序 3,按照销量升序 4,按照销量降序 5,按照价格升序 6 按照价格降序 7,按照人气升序 8 按照人气降序
    public int page;  //当前请求的页数
    public int pageSize;  //当前请求的大小

}
