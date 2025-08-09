package com.example.eduguide10;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Course {

    @SerializedName("file_name")
    private String fileName;

    @SerializedName("title")
    private String title;

    @SerializedName("About")
    private List<String> about;

    @SerializedName("Further Studies")
    private List<String> furtherStudies;

    @SerializedName("Competitive Exams")
    private List<String> competitiveExams;

    @SerializedName("Jobs")
    private List<String> jobs;

    @SerializedName("Summary")
    private List<String> summary;

    public String getFileName() { return fileName; }
    public String getTitle() { return title; }
    public List<String> getAbout() { return about; }
    public List<String> getFurtherStudies() { return furtherStudies; }
    public List<String> getCompetitiveExams() { return competitiveExams; }
    public List<String> getJobs() { return jobs; }
    public List<String> getSummary() { return summary; }
}
