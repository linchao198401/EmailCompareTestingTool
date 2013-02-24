package org.performance.web.common;

import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="mailto:vdanielliu@gmail.com">Daniel</a>
 * 
 */
public class PageResult<T> implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5190190969525462227L;

    private int totalResultCount;

    private int totalPageCount;

    private int currentPage;

    private int maxCountPerPage;

    private List<T> result;

    public PageResult(int totalResultCount, int currentPage, int maxCountPerPage, List<T> result) {
        if (currentPage < 1 || maxCountPerPage < 1 || totalResultCount < 0) {
            throw new IllegalArgumentException();
        }
        this.totalResultCount = totalResultCount;
        this.currentPage = currentPage;
        this.maxCountPerPage = maxCountPerPage;
        this.result = result;
        init();
    }

    private void init() {
        int i = totalResultCount % maxCountPerPage;
        if (i != 0) {
            totalPageCount = totalResultCount / maxCountPerPage + 1;
        } else {
            totalPageCount = totalResultCount / maxCountPerPage;
        }
        if (totalResultCount == 0) {
            totalPageCount = 1;
        }

        if (currentPage > totalPageCount) {
            currentPage = totalPageCount;
        }
    }

    public int getTotalResultCount() {
        return totalResultCount;
    }

    public void setTotalResultCount(int totalResultCount) {
        this.totalResultCount = totalResultCount;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public int getMaxCountPerPage() {
        return maxCountPerPage;
    }

    public void setMaxCountPerPage(int maxCountPerPage) {
        this.maxCountPerPage = maxCountPerPage;
    }

}
