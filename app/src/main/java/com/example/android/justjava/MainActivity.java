package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCheckBox = (CheckBox) findViewById(R.id.whipped_cream_check_box);
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_cream_check_box);
        EditText nameEditText = (EditText) findViewById(R.id.name_edit_text);
        boolean isRaining = true;

        String orderName = nameEditText.getText().toString();
        boolean whipped = whippedCheckBox.isChecked();
        boolean chocolate = chocolateCheckBox.isChecked();
        Log.v("MyActivity", "" + whipped);
        int price = calculatePice();

        if(whipped)
            price += 1 * quantity;

        if(chocolate)
            price += 2 * quantity;


        String message = createOrderSummary(price, whipped, chocolate, orderName);
        displayMessage(message);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:pvlvcdavid@gmail.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_summary_email_subject, orderName));
        intent.putExtra(Intent.EXTRA_TEXT, message);

        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method creates String message
     */
    public String createOrderSummary(int num, boolean wCream, boolean choco, String name) {
        return "Name: " + name + "\nAdd whipped cream: " + wCream + "\nChocolate: " + choco + "\nQuantaty: " + quantity + "\nTotal: $" + num + "\n" + getString(R.string.thank_you);
    }
    /**
     * This method return price of coffees
     */
    private int calculatePice() {
        int price = quantity * 5;
        if (quantity > 100) {
            price = 0;
        } else if (quantity < 1) {
            price = 0;
        } else {
            return price;
        }
        return price;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    public void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays give text on the screen
     */
    public void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method
     */

    /**
     * This method increment quantity
     */
    public void increment(View view) {
        quantity++;
        displayQuantity(quantity);
    }

    /**
     * This method decrement quantity
     */
    public void decrement(View view) {
        quantity--;
        displayQuantity(quantity);
    }
}
