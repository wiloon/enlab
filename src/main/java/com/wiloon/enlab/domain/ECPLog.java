package com.wiloon.enlab.domain;

public class ECPLog {
    private int id;
    private int wordId;
    private String message;
    private LogType logType;

    public ECPLog() {
    }

    public ECPLog(int wordId, LogType logType) {
        this.wordId = wordId;
        this.logType = logType;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LogType getLogType() {
        return logType;
    }

    public void setLogType(LogType logType) {
        this.logType = logType;
    }

    public String toString() {
        return "id=" + id + ",word id=" + wordId + ",message=" + message + ",logType=" + logType;


    }

}
