package com.example.sa.Visitor;


import androidx.appcompat.app.AppCompatActivity;

import com.example.sa.ConnectCan;
import com.example.sa.LoginActivity;
import com.example.sa.NonRegistLocation;
import com.example.sa.NonRegistTrashcan;
import com.example.sa.RedgistMoney;
import com.example.sa.RegistLocation;
import com.example.sa.RegistTrashcan;
import com.example.sa.store.UserStore;

public class LoginVisitor extends AppCompatActivity implements Visitor {

    @Override
    public void visit(RedgistMoneyMediary redgistMoneyMediary) {
        redgistMoneyMediary.gotoPage(RedgistMoney.class);
    }

    @Override
    public void visit(ConnectCanMediary connectCanMediary) {
        connectCanMediary.gotoPage(ConnectCan.class);
    }

    @Override
    public void visit(RegistLocationMediary registLocationMediary) {
        registLocationMediary.gotoPage(RegistLocation.class);
    }

    @Override
    public void visit(RegistTrashcanMediary registTrashcanMediary) {
        registTrashcanMediary.gotoPage(RegistTrashcan.class);
    }

}
