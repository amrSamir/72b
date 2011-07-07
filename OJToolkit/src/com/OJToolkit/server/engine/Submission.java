/**
 * 
 */
package com.OJToolkit.server.engine;

import java.util.Date;

/**
 * @author 72B
 *         May 18, 2011
 */
public class Submission {
    private String problemId;
    private String date;
    private String runtime;
    private String memoryUsed;
    private String status;
    private String language;
    public Submission(){}
    public Submission(String problemId ,String date , String runtime , String memoryUsed , String status , String language)
    {
            this.problemId = problemId;
            this.date = date ;
            this.runtime = runtime;
            this.memoryUsed = memoryUsed;
            this.status = status;
            this.language = language;
    }
    public String getProblemId() {
            return problemId;
    }
    public void setProblemId(String problemId) {
            this.problemId = problemId;
    }
    public String getDate() {
            return date;
    }
    public void setDate(String date) {
            this.date = date ;
    }
    public String getRuntime() {
            return runtime;
    }
    public void setRuntime(String runtime) {
            this.runtime = runtime;
    }
    public String getMemoryUsed() {
            return memoryUsed;
    }
    public void setMemoryUsed(String memoryUsed) {
            this.memoryUsed = memoryUsed;
    }
    public String getStatus() {
            return status;
    }
    public void setStatus(String status) {
            this.status = status;
    }
    public void setLanguage(String language) {
            this.language = language;
    }
    public String getLanguage() {
            return language;
    }
    
}
