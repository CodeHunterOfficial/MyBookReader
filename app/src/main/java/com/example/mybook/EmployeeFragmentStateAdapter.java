package com.example.mybook;

import android.content.Context;
import android.media.MediaPlayer;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class EmployeeFragmentStateAdapter extends FragmentStateAdapter {
private FragmentActivity _fragmentActivity;
    private List<Employee> employees;


    public EmployeeFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.employees = this.intDatas();
        _fragmentActivity=fragmentActivity;
    }

    private List<Employee> intDatas()  {
        Employee emp1 = new Employee("Alizosa Vali", "alizoda2020@example.com", "Web Designer");
        Employee emp2 = new Employee("Safarzoda Rustam", "safarzoda2121@example.com", "Project Manager");
        Employee emp3 = new Employee("Askarzoda Aslam", "askarzoda@example.com", "President of Sales");
        Employee emp4 = new Employee("Muinzoda Mosafar", "muinzoda@example.com", "Mobile Designer");

        List<Employee> list = new ArrayList<Employee>();
        list.add(emp1);
        list.add(emp2);
        list.add(emp3);
        list.add(emp4);
        return list;


    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        play();
        Employee employee = this.employees.get(position);

       /* Toast toast = Toast.makeText(getApplicationContext(),
                "Пора покормить кота!", Toast.LENGTH_SHORT);
        toast.show();*/


        return new EmployeePageFragment(employee);
    }

    public void play(){
        MediaPlayer mPlayer= MediaPlayer.create(getApplicationContext(), R.raw.listsound);
        mPlayer.start();
    }

    @Override
    public int getItemCount() {
        return this.employees.size();
    }

    public Context getApplicationContext() {
        return _fragmentActivity.getApplicationContext();
    }
}