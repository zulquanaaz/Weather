package com.example.weather;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class RegisterFragment extends Fragment {

    Button btn_register;
    TextView txt_oldUser;
    EditText edt_name,edt_email,edt_pass,edt_cpass;
    public NavController navController;
    FirebaseAuth auth;
    FirebaseFirestore db;
    String name,email,pass,cPass;

    public RegisterFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        auth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();

        txt_oldUser=view.findViewById(R.id.txt_login);
        edt_name=view.findViewById(R.id.edit_registerName);
        edt_email=view.findViewById(R.id.edit_registerEmail);
        edt_pass=view.findViewById(R.id.edit_registerPass);
        edt_cpass=view.findViewById(R.id.edit_registerCPass);

        btn_register=view.findViewById(R.id.btn_register);

        btn_register.setOnClickListener(registerUser);
        txt_oldUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController= Navigation.findNavController(getActivity(),R.id.nav_host_fragment_start);
                navController.navigate(R.id.loginFragment);
            }
        });
    }

    View.OnClickListener registerUser=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            name=edt_name.getText().toString().trim();
            email=edt_email.getText().toString().trim();
            pass=edt_pass.getText().toString().trim();
            cPass=edt_cpass.getText().toString().trim();


            final Map<String,Object> usermap=new HashMap<>();
            usermap.put("Name",name);
            usermap.put("Email",email);


            if (!checkEmptyField()){
                auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = auth.getCurrentUser();
                            db.collection("Users").document(user.getUid()).set(usermap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getActivity().getApplicationContext(), "Successfully Registered!", Toast.LENGTH_LONG).show();
                                    NavController navController= Navigation.findNavController(getActivity(),R.id.nav_host_fragment_start);
                                    navController.navigate(R.id.loginFragment);
                                }
                            });
                        }else {
                            try {
                                throw  task.getException();
                            }catch (FirebaseAuthUserCollisionException already) {
                                Toast.makeText(getActivity().getApplicationContext(),"User Already Exist! Please login",Toast.LENGTH_LONG).show();
                                NavController navController=Navigation.findNavController(getActivity(),R.id.nav_host_fragment_start);
                                navController.navigate(R.id.loginFragment);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }



        }
    };

    public boolean checkEmptyField() {

        if (TextUtils.isEmpty(name)) {
            edt_name.setError("Name can't be Empty!");
            edt_name.requestFocus();
            return true;
        }else if (TextUtils.isEmpty(email)) {
            edt_name.setError(null);
            edt_email.setError("Email can't be Empty!");
            edt_email.requestFocus();
            return true;
        } else if (TextUtils.isEmpty(pass)) {
            edt_email.setError(null);
            edt_pass.setError("Password can't be Empty!");
            edt_pass.requestFocus();
            return true;
        }else if (TextUtils.isEmpty(cPass)){
            edt_pass.setError(null);
            edt_cpass.setError("Confirm password can't be Empty!");
            edt_cpass.requestFocus();
            return true;
        }else if (pass.length()<6){
            edt_pass.getText().clear();
            edt_pass.setError("Password can't be less than 6 characters");
            edt_pass.requestFocus();
            return true;
        }else if (!pass.equals(cPass)){
            edt_pass.getText().clear();
            edt_cpass.getText().clear();
            edt_pass.setError("Password doesn't match");
            edt_pass.requestFocus();
            return true;
        }
        return false;
    }
}