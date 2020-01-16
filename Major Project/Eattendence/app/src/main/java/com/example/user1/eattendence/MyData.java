package com.example.user1.eattendence;

import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyData {

    static String[] subSem1 = {"sub1", "sub2"};
    static String[] subSem2 = {"sub1", "sub2", "sub3"};
    static String[] subSem3 = {"sub1", "sub2", "sub3", "sub4"};
    static String[] subSem4 = {"sub1", "sub2", "sub3", "sub4", "sub5"};
    static String[] subSem5 = {"sub1", "sub2", "sub3", "sub4", "sub5", "sub6"};
    static String[] subSem6 = {"ARDBMS(Theory)", "Artificial Intelligence(Theory)", "Visual Basic(Theory)", "ICT(Theory)", "Cyber Security(Theory)"};
    static String[] sem6mon= {"Cyber Security(Theory)","Artificial Intelligence(Theory)","RDBMS-LAB(1st group)","VB-LAB(2nd group)"};
    static String[] sem6tue= {"ARDBMS(Theory)","Artificial Intelligence(Theory)","VB-LAB(1st group)","RDBMS-LAB(2nd group)"};
    static String[] sem6wed= {"Cyber Security(Theory)","Visual Programming(Theory)"};
    static String[] sem6thur= {"Visual Programming(Theory)","ICT(Theory)","Major-Project(1st group)","Major-Project(2nd group)","Major-Project(3rd group)"};
    static String[] sem6fri= {"ARDBMS(Theory)","ICT(Theory)","Major-Project(1st group)","Major-Project(2nd group)","Major-Project(3rd group)"};
    static String[] semArray = {"SELECT", "Sem-I", "Sem-II", "Sem-III", "Sem-IV", "Sem-V", "Sem-VI"};

    static Integer[] id_ = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,
    34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59};
    static Integer[] theory_icon={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30
    ,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60};
    static Integer[] lab_icon={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
    static Integer[] project_icon={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
    public static int key;

    static String[] st_roll={"Roll no: 1","Roll no: 2","Roll no: 3","Roll no: 4","Roll no: 5","Roll no: 6","Roll no: 7","Roll no: 8","Roll no: 9","Roll no: 10","Roll no: 11"
            ,"Roll no: 12","Roll no: 13","Roll no: 14","Roll no: 15","Roll no: 16","Roll no: 17","Roll no: 18","Roll no: 19"
            ,"Roll no: 20","Roll no: 21","Roll no: 22","Roll no: 23","Roll no: 24","Roll no: 25","Roll no: 26"
            ,"Roll no: 27","Roll no: 28","Roll no: 29","Roll no: 30","Roll no: 31","Roll no: 32","Roll no: 33","Roll no: 34"
            ,"Roll no: 35","Roll no: 36","Roll no: 37","Roll no: 38","Roll no: 39","Roll no: 40","Roll no: 41","Roll no: 42"
            ,"Roll no: 43","Roll no: 44","Roll no: 45","Roll no: 46","Roll no: 47","Roll no: 48","Roll no: 49","Roll no: 50","Roll no: 51","Roll no: 52","Roll no: 53"
            ,"Roll no: 54","Roll no: 55","Roll no: 56","Roll no: 57","Roll no: 58","Roll no: 59","Roll no: 60"
    };

    public static void key(int key){
        MyData.key=key;
    }
}
