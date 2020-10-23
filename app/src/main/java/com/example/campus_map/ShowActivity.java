package com.example.campus_map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sfedymob.R;

public class ShowActivity extends AppCompatActivity{
    public class SelectCampus extends AppCompatActivity {
        public String[] Campus={"Чехова 22А","Чехова 22Б","Энгельса 1","Некрасовский пер.44Д","Чехова 2","Шевченко 2","А","Б","Г","Д","И","К"};
        public String[] Dorms={"Александровская 30","Тургеневский пер. 44а","3","7"};
        public Integer[] FloorsNumCampus={2,2,5 ,6 ,4,4};
        public Integer[] CampusFirst=    {0,2,11,5,15,19};
        public Integer[] FloorsNumDorms={4,9};
        public Integer[] DormsFirst=   {32,23};
        public Integer FloorsNumber,ind,type,FirstFloor;
        public ImageButton SelectButton21,SelectButton22,SelectButton60,SelectButton61,SelectButton62,SelectButton63,SelectButton64, SelectButton65,SelectButton50,SelectButton51,
                SelectButton52,SelectButton53,SelectButton54,SelectButton41,SelectButton42,SelectButton43,SelectButton44,SelectButton91,SelectButton92,SelectButton93,SelectButton94,
                SelectButton95,SelectButton96,SelectButton97,SelectButton98,SelectButton99;
        public ImageView imageView1,imageView2,imageView3,imageView4,imageView5;
        public TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10;
        public String Address,Letter;
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getString();
            SelectFunc();
            SelectFloor();
            CampusMapList();
        }
        //---------------------------------Функция сбора значений интента-----------------------------------
        public void getString(){
            Intent i = getIntent();
            ind=i.getIntExtra("pos",1);
            type=i.getIntExtra("ind",1);
        }
        //----------------------------------Выбор типа здания-----------------------------------------------
        public void SelectFunc() {
            switch (type) {
                case 1:
                    FloorsNumber=FloorsNumCampus[ind];
                    FirstFloor=CampusFirst[ind];
                    Address=Campus[ind];
                    Letter="Корпус "+Campus[ind+6];
                    break;
                case 2:FloorsNumber=FloorsNumDorms[ind];
                    FirstFloor=DormsFirst[ind];
                    Address=Dorms[ind];
                    Letter="Общежитие №"+Dorms[ind+2];
                    break;
            }
        }
        //---------------------Выбор активити в зависимости от количества этажей здания---------------------
        public void SelectFloor(){
            switch(FloorsNumber){
                case 2:
                    setContentView(R.layout.two_floors);
                    textView1=findViewById(R.id.textView1);
                    textView6=findViewById(R.id.textView6);
                    imageView1=findViewById(R.id.imageView1);
                    SelectButton21=findViewById(R.id.imageButton1);
                    SelectButton21.setImageResource(R.drawable.floor_one_active);
                    SelectButton22=findViewById(R.id.imageButton2);
                    textView1.setText(Address);
                    textView6.setText(Letter);
                    break;
                case 4:
                    setContentView(R.layout.four_floors);
                    textView2=findViewById(R.id.textView2);
                    textView7=findViewById(R.id.textView7);
                    imageView2=findViewById(R.id.imageView2);
                    SelectButton41=findViewById(R.id.imageButton14);
                    SelectButton41.setImageResource(R.drawable.floor_one_active);
                    SelectButton42=findViewById(R.id.imageButton15);
                    SelectButton43=findViewById(R.id.imageButton16);
                    SelectButton44=findViewById(R.id.imageButton17);
                    textView2.setText(Address);
                    textView7.setText(Letter);
                    break;
                case 5:
                    setContentView(R.layout.five_floors);
                    textView3=findViewById(R.id.textView3);
                    textView8=findViewById(R.id.textView8);
                    imageView4=findViewById(R.id.imageView4);
                    SelectButton50=findViewById(R.id.imageButton9);
                    SelectButton51=findViewById(R.id.imageButton10);
                    SelectButton51.setImageResource(R.drawable.floor_one_active);
                    SelectButton52=findViewById(R.id.imageButton11);
                    SelectButton53=findViewById(R.id.imageButton12);
                    SelectButton54=findViewById(R.id.imageButton13);
                    textView3.setText(Address);
                    textView8.setText(Letter);
                    break;
                case 6:
                    setContentView(R.layout.six_floors);
                    textView4=findViewById(R.id.textView4);
                    textView9=findViewById(R.id.textView9);
                    imageView3=findViewById(R.id.imageView3);
                    SelectButton60=findViewById(R.id.imageButton3);
                    SelectButton61=findViewById(R.id.imageButton4);
                    SelectButton61.setImageResource(R.drawable.floor_one_active);
                    SelectButton62=findViewById(R.id.imageButton5);
                    SelectButton63=findViewById(R.id.imageButton6);
                    SelectButton64=findViewById(R.id.imageButton7);
                    SelectButton65=findViewById(R.id.imageButton8);
                    textView4.setText(Address);
                    textView9.setText(Letter);
                    break;
                case 9:
                    setContentView(R.layout.nine_floors);
                    textView5=findViewById(R.id.textView5);
                    textView10=findViewById(R.id.textView10);
                    imageView5=findViewById(R.id.imageView5);
                    SelectButton91=findViewById(R.id.imageButton18);
                    SelectButton91.setImageResource(R.drawable.floor_one_active);
                    SelectButton92=findViewById(R.id.imageButton19);
                    SelectButton93=findViewById(R.id.imageButton20);
                    SelectButton94=findViewById(R.id.imageButton21);
                    SelectButton95=findViewById(R.id.imageButton22);
                    SelectButton96=findViewById(R.id.imageButton23);
                    SelectButton97=findViewById(R.id.imageButton24);
                    SelectButton98=findViewById(R.id.imageButton25);
                    SelectButton99=findViewById(R.id.imageButton26);
                    textView5.setText(Address);
                    textView10.setText(Letter);
                    break;
            }
        }
        //---------------------------------Иконки выключенных кнопок----------------------------------------
        public void DisableButtons(){
            switch(FloorsNumber){
                case 2:
                    SelectButton21.setImageResource(R.drawable.floor_one);
                    SelectButton22.setImageResource(R.drawable.floor_two);
                    break;
                case 4:
                    SelectButton41.setImageResource(R.drawable.floor_one);
                    SelectButton42.setImageResource(R.drawable.floor_two);
                    SelectButton43.setImageResource(R.drawable.floor_three);
                    SelectButton44.setImageResource(R.drawable.floor_four);
                    break;
                case 5:
                    SelectButton50.setImageResource(R.drawable.floor_zero);
                    SelectButton51.setImageResource(R.drawable.floor_one);
                    SelectButton52.setImageResource(R.drawable.floor_two);
                    SelectButton53.setImageResource(R.drawable.floor_three);
                    SelectButton54.setImageResource(R.drawable.floor_four);
                    break;
                case 6:
                    SelectButton60.setImageResource(R.drawable.floor_zero);
                    SelectButton61.setImageResource(R.drawable.floor_one);
                    SelectButton62.setImageResource(R.drawable.floor_two);
                    SelectButton63.setImageResource(R.drawable.floor_three);
                    SelectButton64.setImageResource(R.drawable.floor_four);
                    SelectButton65.setImageResource(R.drawable.floor_five);
                    break;
                case 9:
                    SelectButton91.setImageResource(R.drawable.floor_one);
                    SelectButton92.setImageResource(R.drawable.floor_two);
                    SelectButton93.setImageResource(R.drawable.floor_three);
                    SelectButton94.setImageResource(R.drawable.floor_four);
                    SelectButton95.setImageResource(R.drawable.floor_five);
                    SelectButton96.setImageResource(R.drawable.floor_six);
                    SelectButton97.setImageResource(R.drawable.floor_seven);
                    SelectButton98.setImageResource(R.drawable.floor_eight);
                    SelectButton99.setImageResource(R.drawable.floor_nine);
                    break;
            }
        }
        //------------------------------------Обработчик нажатий на кнопки----------------------------------
//------------------------------------------two_floors----------------------------------------------
        public void onClick21(View view){
            DisableButtons();
            SelectButton21.setImageResource(R.drawable.floor_one_active);
            CampusMapList();
        }
        public void onClick22(View view){
            DisableButtons();
            SelectButton22.setImageResource(R.drawable.floor_two_active);
            FirstFloor+=1;
            CampusMapList();
            FirstFloor-=1;
        }
        //------------------------------------------four_floors---------------------------------------------
        public void onClick41(View view) {
            DisableButtons();
            SelectButton41.setImageResource(R.drawable.floor_one_active);
            CampusMapList();
        }
        public void onClick42(View view) {
            DisableButtons();
            SelectButton42.setImageResource(R.drawable.floor_two_active);
            FirstFloor+=1;
            CampusMapList();
            FirstFloor-=1;
        }
        public void onClick43(View view) {
            DisableButtons();
            SelectButton43.setImageResource(R.drawable.floor_three_active);
            FirstFloor+=2;
            CampusMapList();
            FirstFloor-=2;
        }
        public void onClick44(View view) {
            DisableButtons();
            SelectButton44.setImageResource(R.drawable.floor_four_active);
            FirstFloor+=3;
            CampusMapList();
            FirstFloor-=3;
        }
        //-----------------------------------------five_floors----------------------------------------------
        public void onClick50(View view) {
            DisableButtons();
            SelectButton50.setImageResource(R.drawable.floor_zero_active);
            FirstFloor -= 1;
            CampusMapList();
            FirstFloor += 1;
        }
        public void onClick51(View view) {
            DisableButtons();
            SelectButton51.setImageResource(R.drawable.floor_one_active);
            CampusMapList();
        }
        public void onClick52(View view) {
            DisableButtons();
            SelectButton52.setImageResource(R.drawable.floor_two_active);
            FirstFloor += 1;
            CampusMapList();
            FirstFloor -= 1;
        }
        public void onClick53(View view) {
            DisableButtons();
            SelectButton53.setImageResource(R.drawable.floor_three_active);
            FirstFloor += 2;
            CampusMapList();
            FirstFloor -= 2;
        }
        public void onClick54(View view) {
            DisableButtons();
            SelectButton54.setImageResource(R.drawable.floor_four_active);
            FirstFloor += 3;
            CampusMapList();
            FirstFloor -= 3;
        }
        //-----------------------------------------six_Floors-----------------------------------------------
        public void onClick60(View view) {
            DisableButtons();
            SelectButton60.setImageResource(R.drawable.floor_zero_active);
            FirstFloor -= 1;
            CampusMapList();
            FirstFloor += 1;
        }
        public void onClick61(View view) {
            DisableButtons();
            SelectButton61.setImageResource(R.drawable.floor_one_active);
            CampusMapList();
        }
        public void onClick62(View view) {
            DisableButtons();
            SelectButton62.setImageResource(R.drawable.floor_two_active);
            FirstFloor += 1;
            CampusMapList();
            FirstFloor -= 1;
        }
        public void onClick63(View view) {
            DisableButtons();
            SelectButton63.setImageResource(R.drawable.floor_three_active);
            FirstFloor += 2;
            CampusMapList();
            FirstFloor -= 2;
        }
        public void onClick64(View view) {
            DisableButtons();
            SelectButton64.setImageResource(R.drawable.floor_four_active);
            FirstFloor += 3;
            CampusMapList();
            FirstFloor -= 3;
        }
        public void onClick65(View view) {
            DisableButtons();
            SelectButton65.setImageResource(R.drawable.floor_five_active);
            FirstFloor += 4;
            CampusMapList();
            FirstFloor -= 4;
        }
        //-----------------------------------------nine_Floors----------------------------------------------
        public void onClick91(View view) {
            DisableButtons();
            SelectButton91.setImageResource(R.drawable.floor_one_active);
            CampusMapList();
        }
        public void onClick92(View view) {
            DisableButtons();
            SelectButton92.setImageResource(R.drawable.floor_two_active);
            FirstFloor += 1;
            CampusMapList();
            FirstFloor -= 1;
        }
        public void onClick93(View view) {
            DisableButtons();
            SelectButton93.setImageResource(R.drawable.floor_three_active);
            FirstFloor += 2;
            CampusMapList();
            FirstFloor -= 2;
        }
        public void onClick94(View view) {
            DisableButtons();
            SelectButton94.setImageResource(R.drawable.floor_four_active);
            FirstFloor += 3;
            CampusMapList();
            FirstFloor -= 3;
        }
        public void onClick95(View view) {
            DisableButtons();
            SelectButton95.setImageResource(R.drawable.floor_five_active);
            FirstFloor += 4;
            CampusMapList();
            FirstFloor -= 4;
        }
        public void onClick96(View view) {
            DisableButtons();
            SelectButton96.setImageResource(R.drawable.floor_six_active);
            FirstFloor += 5;
            CampusMapList();
            FirstFloor -= 5;
        }
        public void onClick97(View view) {
            DisableButtons();
            SelectButton97.setImageResource(R.drawable.floor_seven_active);
            FirstFloor += 6;
            CampusMapList();
            FirstFloor -= 6;
        }
        public void onClick98(View view) {
            DisableButtons();
            SelectButton98.setImageResource(R.drawable.floor_eight_active);
            FirstFloor += 7;
            CampusMapList();
            FirstFloor -= 7;
        }
        public void onClick99(View view) {
            DisableButtons();
            SelectButton99.setImageResource(R.drawable.floor_nine_active);
            FirstFloor += 8;
            CampusMapList();
            FirstFloor -= 8;
        }
        //---------------------------------------Карты корпусов---------------------------------------------
        public void CampusMapList(){
            switch(FirstFloor){
                case 0:
                    imageView1.setImageResource(R.drawable.a_building_1);//CampA
                    break;
                case 1:
                    imageView1.setImageResource(R.drawable.a_building_2);
                    break;
                case 2:
                    imageView1.setImageResource(R.drawable.b_building_1);//CampB
                    break;
                case 3:
                    imageView1.setImageResource(R.drawable.b_building_2);
                    break;
                case 4:
                    imageView3.setImageResource(R.drawable.d_building_0); //CampD
                    break;
                case 5:
                    imageView3.setImageResource(R.drawable.d_building_1);
                    break;
                case 6:
                    imageView3.setImageResource(R.drawable.d_building_2);
                    break;
                case 7:
                    imageView3.setImageResource(R.drawable.d_building_3);
                    break;
                case 8:
                    imageView3.setImageResource(R.drawable.d_building_4);
                    break;
                case 9:
                    imageView3.setImageResource(R.drawable.d_building_5);
                    break;
                case 10:
                    imageView4.setImageResource(R.drawable.g_building_0); //CampG
                    break;
                case 11:
                    imageView4.setImageResource(R.drawable.g_building_1);
                    break;
                case 12:
                    imageView4.setImageResource(R.drawable.g_building_2);
                    break;
                case 13:
                    imageView4.setImageResource(R.drawable.g_building_3);
                    break;
                case 14:
                    imageView4.setImageResource(R.drawable.g_building_4);
                    break;
                case 15:
                    imageView2.setImageResource(R.drawable.i_building_1); //CampI
                    break;
                case 16:
                    imageView2.setImageResource(R.drawable.i_building_2);
                    break;
                case 17:
                    imageView2.setImageResource(R.drawable.i_building_3);
                    break;
                case 18:
                    imageView2.setImageResource(R.drawable.i_building_4);
                    break;
                case 19:
                    imageView2.setImageResource(R.drawable.k_building_1); //CampK
                    break;
                case 20:
                    imageView2.setImageResource(R.drawable.k_building_2);
                    break;
                case 21:
                    imageView2.setImageResource(R.drawable.k_building_3);
                    break;
                case 22:
                    imageView2.setImageResource(R.drawable.k_building_4);
                    break;
                case 23:
                    imageView5.setImageResource(R.drawable.seven_dormitory_1); //Dormitory7
                    break;
                case 24:
                    imageView5.setImageResource(R.drawable.seven_dormitory_2);
                    break;
                case 25:
                    imageView5.setImageResource(R.drawable.seven_dormitory_3);
                    break;
                case 26:
                    imageView5.setImageResource(R.drawable.seven_dormitory_4);
                    break;
                case 27:
                    imageView5.setImageResource(R.drawable.seven_dormitory_5);
                    break;
                case 28:
                    imageView5.setImageResource(R.drawable.seven_dormitory_6);
                    break;
                case 29:
                    imageView5.setImageResource(R.drawable.seven_dormitory_7);
                    break;
                case 30:
                    imageView5.setImageResource(R.drawable.seven_dormitory_8);
                    break;
                case 31:
                    imageView5.setImageResource(R.drawable.seven_dormitory_9);
                    break;
                case 32:
                    imageView2.setImageResource(R.drawable.three_dormitory_1); //Dormitory3
                    break;
                case 33:
                    imageView2.setImageResource(R.drawable.three_dormitory_2);
                    break;
                case 34:
                    imageView2.setImageResource(R.drawable.three_dormitory_3);
                    break;
                case 35:
                    imageView2.setImageResource(R.drawable.three_dormitory_4);
                    break;
            }
        }
    }
}
