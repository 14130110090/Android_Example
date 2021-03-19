package com.example.boxuegu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Trace;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.boxuegu.utils.LogUtils;


public class BaseActivity extends AppCompatActivity {

    public void showShortToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * 重新onKeyDown，监听物理返回键，返回桌面不销毁应用
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        LogUtils.i(keyCode);

//        if (isTaskRoot()&&keyCode == KeyEvent.KEYCODE_BACK) {
//            Intent intent = new Intent(Intent.ACTION_MAIN);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            intent.addCategory(Intent.CATEGORY_HOME);
//            startActivity(intent);
//            return true;
//        }
        return super.onKeyDown(keyCode, event);
    }

    class Solution {
        int result = 0;

        public int pathSum(TreeNode root, int sum) {
            if (root == null) return result;
            dfs(root, sum);
            pathSum(root.left, sum);
            pathSum(root.right, sum);
            return result;
        }

        public void dfs(TreeNode root, int sum) {
            if (root != null) {
                sum -= root.val;
                if (sum == 0) result++;
                dfs(root.left, sum);
                dfs(root.right, sum);
            }
        }
    }
}