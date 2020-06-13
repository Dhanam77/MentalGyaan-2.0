package com.example.mentalgyaan.Model;

public class QuizQuestion_score {
    private  String QuizQuestion_score;
    private String User,Score;

    public QuizQuestion_score() {
    }

    public QuizQuestion_score(String quizQuestion_score, String user, String score) {
        QuizQuestion_score = quizQuestion_score;
        User = user;
        Score = score;
    }

    public String getQuizQuestion_score() {
        return QuizQuestion_score;
    }

    public void setQuizQuestion_score(String quizQuestion_score) {
        QuizQuestion_score = quizQuestion_score;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }
}
