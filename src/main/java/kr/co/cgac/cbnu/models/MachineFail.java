package kr.co.cgac.cbnu.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by cgac_315 on 6/16/2017.
 */
public class MachineFail {
    @JsonProperty("FID")
    private String fId;
    @JsonProperty("FLINE")
    private String fLine;
    @JsonProperty("FMACHINE")
    private String fMachine;
    @JsonProperty("DOWN_TIME")
    private String downTime;
    @JsonProperty("RESTART_TIME")
    private String restartTime;
    @JsonProperty("FCODE")
    private String fCode;
    @JsonProperty("FSUBCODE")
    private String fSubCode;
    @JsonProperty("FDETAIL")
    private String fDetail;
    @JsonProperty("FPHEN")
    private String fPhen;
    @JsonProperty("REPAIRE_DETAIL")
    private String repairDetail;
    @JsonProperty("WORKER")
    private String worker;
    @JsonProperty("WORK_START")
    private String workStart;
    @JsonProperty("WORK_END")
    private String workEnd;
    @JsonProperty("WORK_DURATION")
    private double workDuration;

    public MachineFail() {}

    public MachineFail(String fId, String fLine, String fMachine, String downTime,
                       String restartTime, String fCode, String fSubCode, String fDetail,
                       String fPhen, String repaireDetail, String worker, String workStart,
                       String workEnd, double workDuration) {
        this.fId = fId;
        this.fLine = fLine;
        this.fMachine = fMachine;
        this.downTime = downTime;
        this.restartTime = restartTime;
        this.fCode = fCode;
        this.fSubCode = fSubCode;
        this.fDetail = fDetail;
        this.fPhen = fPhen;
        this.repairDetail = repaireDetail;
        this.worker = worker;
        this.workStart = workStart;
        this.workEnd = workEnd;
        this.workDuration = workDuration;
    }

    @Override
    public String toString() {
        return "MachineFail{" +
                "fId='" + fId + '\'' +
                ", fLine='" + fLine + '\'' +
                ", fMachine='" + fMachine + '\'' +
                ", downTime='" + downTime + '\'' +
                ", restartTime='" + restartTime + '\'' +
                ", fCode='" + fCode + '\'' +
                ", fSubCode='" + fSubCode + '\'' +
                ", fDetail='" + fDetail + '\'' +
                ", fPhen='" + fPhen + '\'' +
                ", repairDetail='" + repairDetail + '\'' +
                ", worker='" + worker + '\'' +
                ", workStart='" + workStart + '\'' +
                ", workEnd='" + workEnd + '\'' +
                ", workDuration=" + workDuration +
                '}';
    }

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId;
    }

    public String getfLine() {
        return fLine;
    }

    public void setfLine(String fLine) {
        this.fLine = fLine;
    }

    public String getfMachine() {
        return fMachine;
    }

    public void setfMachine(String fMachine) {
        this.fMachine = fMachine;
    }

    public String getDownTime() {
        return downTime;
    }

    public void setDownTime(String downTime) {
        this.downTime = downTime;
    }

    public String getRestartTime() {
        return restartTime;
    }

    public void setRestartTime(String restartTime) {
        this.restartTime = restartTime;
    }

    public String getfCode() {
        return fCode;
    }

    public void setfCode(String fCode) {
        this.fCode = fCode;
    }

    public String getfSubCode() {
        return fSubCode;
    }

    public void setfSubCode(String fSubCode) {
        this.fSubCode = fSubCode;
    }

    public String getfDetail() {
        return fDetail;
    }

    public void setfDetail(String fDetail) {
        this.fDetail = fDetail;
    }

    public String getfPhen() {
        return fPhen;
    }

    public void setfPhen(String fPhen) {
        this.fPhen = fPhen;
    }

    public String getRepairDetail() {
        return repairDetail;
    }

    public void setRepairDetail(String repairDetail) {
        this.repairDetail = repairDetail;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public String getWorkStart() {
        return workStart;
    }

    public void setWorkStart(String workStart) {
        this.workStart = workStart;
    }

    public String getWorkEnd() {
        return workEnd;
    }

    public void setWorkEnd(String workEnd) {
        this.workEnd = workEnd;
    }

    public double getWorkDuration() {
        return workDuration;
    }

    public void setWorkDuration(double workDuration) {
        this.workDuration = workDuration;
    }
}
