package zy.com.cn.demo.take.task.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @title: BaseModel
 * @description: 基础类
 * @author: zhangyan
 * @date: 2020-03-10 15:57
 * @version: 1.0
 **/
public class BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 创建时间
     */
    private Date createdDate;
    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 删除标志
     */
    private String deletedFlag;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getDeletedFlag() {
        return deletedFlag;
    }

    public void setDeletedFlag(String deletedFlag) {
        this.deletedFlag = deletedFlag;
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", createdBy='" + createdBy + '\'' +
                ", deletedFlag='" + deletedFlag + '\'' +
                '}';
    }
}
