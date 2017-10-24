package com.alg.ailigou.common.model;

import java.util.List;

/**
 * Created by 海航
 * on 2017/8/15.
 * 此类或接口用于  利购券余额的model
 */

public class LigouOverageModelEX {


    /**
     * count : 100.0
     * dataList : [{"count":808,"obtainDetails":"赠送发放：808元 X 800个赠送权","obtainTime":1.486656087E12,"obtainType":9},{"count":816,"obtainDetails":"赠送发放：816元 X 800个赠送权","obtainTime":1.486742498E12,"obtainType":9},{"count":822.97,"obtainDetails":"赠送发放：822.97元 X 799个赠送权","obtainTime":1.486828909E12,"obtainType":9},{"count":2037.96,"obtainDetails":"赠送发放：2037.96元 X 1998个赠送权","obtainTime":1.486915261E12,"obtainType":9},{"count":2015.96,"obtainDetails":"赠送发放：2015.96元 X 1996个赠送权","obtainTime":1.487001673E12,"obtainType":9},{"count":2013.94,"obtainDetails":"赠送发放：2013.94元 X 1994个赠送权","obtainTime":1.487088084E12,"obtainType":9},{"count":2031.84,"obtainDetails":"赠送发放：2031.84元 X 1992个赠送权","obtainTime":1.487174496E12,"obtainType":9},{"count":2009.9,"obtainDetails":"赠送发放：2009.9元 X 1990个赠送权","obtainTime":1.48726091E12,"obtainType":9},{"count":1988,"obtainDetails":"赠送发放：1988元 X 1988个赠送权","obtainTime":1.487347261E12,"obtainType":9},{"count":2025.72,"obtainDetails":"赠送发放：2025.72元 X 1986个赠送权","obtainTime":1.487433676E12,"obtainType":9},{"count":2003.84,"obtainDetails":"赠送发放：2003.84元 X 1984个赠送权","obtainTime":1.487520088E12,"obtainType":9},{"count":1982,"obtainDetails":"赠送发放：1982元 X 1982个赠送权","obtainTime":1.4876065E12,"obtainType":9},{"count":1999.8,"obtainDetails":"赠送发放：1999.8元 X 1980个赠送权","obtainTime":1.487692912E12,"obtainType":9},{"count":1978,"obtainDetails":"赠送发放：1978元 X 1978个赠送权","obtainTime":1.487779264E12,"obtainType":9},{"count":1995.76,"obtainDetails":"赠送发放：1995.76元 X 1976个赠送权","obtainTime":1.487865677E12,"obtainType":9},{"count":2256.34,"obtainDetails":"赠送发放：2256.34元 X 2234个赠送权","obtainTime":1.48795209E12,"obtainType":9},{"count":2276.64,"obtainDetails":"赠送发放：2276.64元 X 2232个赠送权","obtainTime":1.488038503E12,"obtainType":9},{"count":2251.29,"obtainDetails":"赠送发放：2251.29元 X 2229个赠送权","obtainTime":1.488124916E12,"obtainType":9},{"count":2227,"obtainDetails":"赠送发放：2227元 X 2227个赠送权","obtainTime":1.488211269E12,"obtainType":9},{"count":2269.5,"obtainDetails":"赠送发放：2269.5元 X 2225个赠送权","obtainTime":1.488297681E12,"obtainType":9}]
     * hasNext : true
     * page : 1.0
     * pageSize : 20.0
     */

    private double count;
    private boolean hasNext;
    private double page;
    private double pageSize;
    private List<DataListBean> dataList;

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public double getPage() {
        return page;
    }

    public void setPage(double page) {
        this.page = page;
    }

    public double getPageSize() {
        return pageSize;
    }

    public void setPageSize(double pageSize) {
        this.pageSize = pageSize;
    }

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class DataListBean {
        /**
         * count : 808.0
         * obtainDetails : 赠送发放：808元 X 800个赠送权
         * obtainTime : 1.486656087E12
         * obtainType : 9.0
         */

        private double count;
        private String obtainDetails;
        private double obtainTime;
        private double obtainType;

        public double getCount() {
            return count;
        }

        public void setCount(double count) {
            this.count = count;
        }

        public String getObtainDetails() {
            return obtainDetails;
        }

        public void setObtainDetails(String obtainDetails) {
            this.obtainDetails = obtainDetails;
        }

        public double getObtainTime() {
            return obtainTime;
        }

        public void setObtainTime(double obtainTime) {
            this.obtainTime = obtainTime;
        }

        public double getObtainType() {
            return obtainType;
        }

        public void setObtainType(double obtainType) {
            this.obtainType = obtainType;
        }
    }
}
