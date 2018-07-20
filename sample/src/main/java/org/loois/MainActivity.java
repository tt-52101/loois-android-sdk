package org.loois;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.loois.dapp.protocol.core.LooisSocketImpl;
import org.loois.dapp.protocol.core.SocketListener;
import org.loois.dapp.protocol.core.socket.SocketBalance;
import org.loois.dapp.protocol.core.socket.SocketPendingTx;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    public static final String WALLET_ADDRESS = "0xeaeec75ba0880a44edc5460e1d91c59a9da6bbc7";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        testSocketBalance();

        testSocketMarketcap();
    }

    private void testSocketMarketcap() {
        LooisSocketImpl socket = new LooisSocketImpl();
        socket.onMarketCap("CNY");
        socket.registerMarketCapListener(new SocketListener(){
            @Override
            public void onMarketCap(String result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void testSockePendingTx() {
        LooisSocketImpl looisSocket = new LooisSocketImpl();
        looisSocket.onPendingTx(WALLET_ADDRESS);
        looisSocket.registerTransactionListener(new SocketListener(){

            @Override
            public void onPendingTx(SocketPendingTx result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,result.code , Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void testSocketBalance() {
        LooisSocketImpl looisSocket = new LooisSocketImpl();
        looisSocket.onBalance(WALLET_ADDRESS);
        looisSocket.registerBalanceListener(new SocketListener(){
            @Override
            public void onBalance(SocketBalance result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, result.getTokens().get(0).getSymbol(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
