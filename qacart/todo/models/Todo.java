package com.qacart.todo.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude( JsonInclude.Include.NON_DEFAULT)
public class Todo {
    @JsonInclude( JsonInclude.Include.NON_NULL)
    @JsonProperty("isCompleted")
    private Boolean isCompleted;
    private String item;
    @JsonProperty("_id")
    private String id;
    @JsonProperty("userID")
    private String UserID;
    private String createdAt;
    @JsonProperty("__v")
    private String v;

    public Todo ( )
        {
        }

    public Todo ( String item )
        {
            this.item = item;
        }

    public Todo ( Boolean isCompleted , String item )
        {
            this.isCompleted = isCompleted;
            this.item = item;
        }

    @JsonProperty("isCompleted")
    public Boolean getIsCompleted ( )
        {
            return isCompleted;
        }
    @JsonProperty("isCompleted")
    public void setIsCompleted ( Boolean isCompleted )
        {
          this.isCompleted  = isCompleted;
        }
    public String getItem ( )
        {
            return item;
        }

    public void setItem ( String item )
        {
            this.item = item;
        }
    @JsonProperty("_id")
    public String getId ( )
        {
            return id;
        }

    public void setId ( String id )
        {
            this.id = id;
        }
    @JsonProperty("userID")
    public String getUserID ( )
        {
            return UserID;
        }
    @JsonProperty("userID")
    public void setUserID ( String UserID )
        {
            this.UserID = UserID;
        }

    public String getCreatedAt ( )
        {
            return createdAt;
        }

    public void setCreatedAt ( String createdAt )
        {
            this.createdAt = createdAt;
        }
    @JsonProperty("__v")
    public String getV ( )
        {
            return v;
        }

    public void setV ( String v )
        {
            this.v = v;
        }




}
