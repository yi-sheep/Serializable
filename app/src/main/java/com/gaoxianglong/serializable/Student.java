package com.gaoxianglong.serializable;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 5903697428193724130L; // 这个是系列化的版本号，如果不写就会默认生成，但是每一次修改类结构都会随机一个，导致修改后数据无法读取
    // 默认是全部序列化，在变量前面使用transient表示这个变量只是暂时的，不需要序列化
    private String name;
    private int age;
    private Score score;

    public Student(String name, int age, Score score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}

class Score implements Serializable {
    private static final long serialVersionUID = 5003464518029273019L;
    private int math;
    private int english;
    private int chinese;
    private String grade;

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public int getChinese() {
        return chinese;
    }

    public void setChinese(int chinese) {
        this.chinese = chinese;
    }

    public String getGrade() {
        return grade;
    }

    public Score(int math, int english, int chinese) {
        this.math = math;
        this.english = english;
        this.chinese = chinese;
        if (math>90&&english>90&&chinese>90) {
            this.grade = "A";
        } else if (math > 80 && english > 80 && chinese > 80) {
            this.grade = "B";
        } else {
            this.grade = "C";
        }

    }
}
