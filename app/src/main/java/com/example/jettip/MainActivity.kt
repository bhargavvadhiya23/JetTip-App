package com.example.jettip

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jettip.Util.calculateToatlTip
import com.example.jettip.Util.calculateTotalPerPerson
import com.example.jettip.components.InputField
import com.example.jettip.ui.theme.JetTipTheme
import com.example.jettip.widgets.RoundIconBtn

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Myapp {
                Column(modifier = Modifier.fillMaxHeight().fillMaxWidth()) {

                    MainContent()
                }
            }

        }
    }
}



@Composable
fun Myapp(content: @Composable () -> Unit) {
    JetTipTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }

}
@Preview
@Composable
fun TopHeader(amountPerPerson: Double = 0.0) {
    var x = remember {
        mutableDoubleStateOf(0.0)
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp)
            .height(150.dp).
                padding(
                    horizontal = 24.dp
                )
            .clip(shape = RoundedCornerShape(corner = CornerSize(12.dp))),
        color = Color(0xFFE9D7F7)
    ) {
        Column(
            modifier = Modifier.padding(4.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
             x.doubleValue = "%.2f".format(amountPerPerson).toDouble()

            Text(
                "Total Per Person",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )

            Text(
                "$${x.doubleValue}",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.W700,
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainContent() {
    BillForm { billAmt ->
        Log.d("", "MainContent: ${billAmt.toInt()}")

    }

}


@Composable
fun BillForm(modifier: Modifier = Modifier, onVAlueChange: (String) -> Unit = {}) {



    val totalBillState = remember {
        mutableStateOf("")
    }
    val tipAmountState = remember {
        mutableDoubleStateOf(0.0)
    }
    val sliderPosState = remember {
        mutableDoubleStateOf(0.0)
    }
    val splitConter = remember {
        mutableIntStateOf(1)
    }

    val validateState = remember(totalBillState.value) {
        totalBillState.value.trim().isNotEmpty()

    }

    val totalPerPersonState = remember {
        mutableDoubleStateOf(0.0)
    }
    val keyboardController = LocalSoftwareKeyboardController.current
Column {
    TopHeader(totalPerPersonState.doubleValue)
    Surface(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth(),
        border = BorderStroke(1.dp, color = Color.LightGray),
        shape = RoundedCornerShape(corner = CornerSize(12.dp))
    ) {
        Column(
            modifier.padding(6.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            InputField(valuState = totalBillState,
                labelId = "Enter Bill",
                enabled = true,
                isSigleLine = true,
                onAction = KeyboardActions {
                    if (!validateState) return@KeyboardActions
                    onVAlueChange(totalBillState.value.trim())
                    keyboardController?.hide()
                })
            if (validateState) {
                tipAmountState.doubleValue = calculateToatlTip(
                    totalBillState.value.toDouble(),
                    (sliderPosState.doubleValue * 100).toInt()
                )
                totalPerPersonState.doubleValue = calculateTotalPerPerson(
                    totalBill = totalBillState.value.toDouble(),
                    splitby = splitConter.intValue,
                    tipAmount = tipAmountState.doubleValue
                )
                Row(modifier = Modifier.padding(3.dp), horizontalArrangement = Arrangement.Start) {
                    Text("Split", modifier = Modifier.align(alignment = Alignment.CenterVertically))
                    Spacer(modifier = Modifier.width(120.dp))
                    Row(
                        modifier.padding(horizontal = 3.dp), horizontalArrangement = Arrangement.End
                    ) {
                        RoundIconBtn(imageVector = Icons.Default.Remove, onClickAction = {
                            if (splitConter.value > 1) {
                                splitConter.value -= 1
                                totalPerPersonState.doubleValue = calculateTotalPerPerson(
                                    totalBill = totalBillState.value.toDouble(),
                                    splitby = splitConter.intValue,
                                    tipAmount = tipAmountState.doubleValue
                                )
                            }

                        })


                        Text(
                            splitConter.value.toString(),
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(horizontal = 9.dp)
                        )


                        RoundIconBtn(imageVector = Icons.Default.Add, onClickAction = { /*TODO*/
                            if (splitConter.value < 100)
                                splitConter.value++
                            totalPerPersonState.doubleValue = calculateTotalPerPerson(
                                totalBill = totalBillState.value.toDouble(),
                                splitby = splitConter.intValue,
                                tipAmount = tipAmountState.doubleValue
                            )
                        })
                    }
                }
                Row(modifier.padding(3.dp, vertical = 12.dp)) {
                    Text(
                        "Tip Amount",
                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                    )
                    Spacer(
                        modifier
                            .width(125.dp)
                            .height(20.dp)
                    )
                    Text("${tipAmountState.value}")
                }
                Column(
                    modifier
                        .padding(13.dp)
                        .height(120.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("${(sliderPosState.value * 100).toInt()}%")
                    Spacer(modifier.height(12.dp))


                    //Slider
                    Slider(value = sliderPosState.value.toFloat(), onValueChange = { newVal ->
                        sliderPosState.doubleValue = newVal.toDouble()
                        totalPerPersonState.doubleValue = calculateTotalPerPerson(
                            totalBill = totalBillState.value.toDouble(),
                            splitby = splitConter.intValue,
                            tipAmount = tipAmountState.doubleValue
                        )
                        tipAmountState.doubleValue = calculateToatlTip(
                            totalBillState.value.toDouble(),
                            (sliderPosState.doubleValue * 100).toInt()
                        )
                    })
                }
            }
        }
    }
}}
