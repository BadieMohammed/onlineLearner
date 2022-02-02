package de.unidue.inf.is.domain;

public class ListOfTasks {
    private String nameOfTask;
    private String myDelivery;
    private int theGrade;

    public ListOfTasks(String nameOfTask, String myDelivery, int theGrade) {
        this.nameOfTask = nameOfTask;
        this.myDelivery = myDelivery;
        this.theGrade = theGrade;
    }

    public String getNameOfTask() {
        return nameOfTask;
    }

    public void setNameOfTask(String nameOfTask) {
        this.nameOfTask = nameOfTask;
    }

    public String getMyDelivery() {
        return myDelivery;
    }

    public void setMyDelivery(String myDelivery) {
        this.myDelivery = myDelivery;
    }

    public int getTheGrade() {
        return theGrade;
    }

    public void setTheGrade(int theGrade) {
        this.theGrade = theGrade;
    }
}
