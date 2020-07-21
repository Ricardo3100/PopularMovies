package com.example.popularmovies;

import java.util.ArrayList;

public class RecyclerViewModelData {
    private String mOriginal_Title;
    private String mRelease_Date;
    private String mMovie_Poster;

    private String mVote_Average;
    private String mOverview;

    public RecyclerViewModelData(String Original_Title, String Release_Date, String Movie_Poster, String Vote_Average,
                                 String Overview) {
        mOriginal_Title = Original_Title;
        mRelease_Date = Release_Date;
        mMovie_Poster = Movie_Poster;
        mVote_Average = Vote_Average;
        mOverview = Overview;
    }

    public String getOriginal_Title() {
        return mOriginal_Title;
    }

    public String getRelease_Date() {
        return mRelease_Date;
    }

    public String getMovie_Poster() {
        return mMovie_Poster;
    }

    public String getVote_Average() {
        return mVote_Average;
    }

    public String getOverview() {
        return mOverview;
    }
}


//
//    private static int lastContactId = 0;
//
//    public static ArrayList<RecyclerViewData> createContactsList(int numContacts) {
//        ArrayList<RecyclerViewData> MovieDetails = new ArrayList<RecyclerViewData>();
//
//        for (int i = 1; i <= numContacts; i++) {
//            MovieDetails.add(new RecyclerViewData("Person " + ++lastContactId, i <= numContacts / 2));
//        }
//
//        return MovieDetails;
//    }
//}
//
//
//
//}
