package com.pandorina.hal_fiyatlari.presentation.screens.earnings.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.compose.rememberNavController
import com.pandorina.hal_fiyatlari.domain.model.earning.Earning
import com.pandorina.hal_fiyatlari.presentation.screens.earnings.mEarning
import com.pandorina.hal_fiyatlari.presentation.theme.black
import com.pandorina.hal_fiyatlari.presentation.theme.white

@Composable
@Preview
fun EarningItem(
    modifier: Modifier = Modifier,
    earning: Earning = Earning(
        1,
        "Patlıcan",
        14f,
        850f,
        48f,
        9800f,
        5780f,
        5946464816487
    ),
    onClickEdit: (Earning?) -> Unit = {},
    onClickDelete: (Int?) -> Unit = {},
    navController: NavController = rememberNavController()
) {
    Card(
        elevation = 8.dp,
        modifier = modifier
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val (refName, refTotalAmount, refUnitPrice,
                refTotalPrice, refDate, refEdit, refDelete) = createRefs()

            Text(
                fontWeight = FontWeight.SemiBold,
                text = earning.name,
                modifier = Modifier
                    .constrainAs(refName) {
                        start.linkTo(parent.start, 8.dp)
                        top.linkTo(parent.top, 8.dp)
                    })

            Text(
                fontWeight = FontWeight.Normal,
                text = "\uD83D\uDCCA ${earning.totalCaseCount.toInt()} Kasa " +
                        "- ${(earning.caseWeight * earning.totalCaseCount).toInt()} Kilogram " +
                        "- ${earning.unitPrice} ₺",
                modifier = Modifier
                    .constrainAs(refTotalAmount) {
                        start.linkTo(parent.start, 8.dp)
                        top.linkTo(refName.bottom)
                    })

            Text(
                fontWeight = FontWeight.Normal,
                text = "\uD83D\uDD52 ${earning.date}",
                modifier = Modifier
                    .constrainAs(refUnitPrice) {
                        start.linkTo(parent.start, 8.dp)
                        top.linkTo(refTotalAmount.bottom)
                        bottom.linkTo(parent.bottom, 8.dp)
                    })

            Text(
                fontWeight = FontWeight.Bold,
                text = "${earning.totalIncome} ₺",
                fontSize = 18.sp,
                modifier = Modifier
                    .constrainAs(refTotalPrice) {
                        end.linkTo(parent.end, 8.dp)
                        top.linkTo(parent.top, 8.dp)
                    })

            Icon(
                Icons.Default.Edit,
                contentDescription = "",
                modifier = Modifier
                    .constrainAs(refEdit) {
                        end.linkTo(parent.end, 8.dp)
                        bottom.linkTo(parent.bottom, 8.dp)
                    }
                    .size(22.dp)
                    .clickable {
                        onClickEdit(earning)
                    }
            )

            Icon(
                Icons.Default.Delete,
                contentDescription = "",
                modifier = Modifier
                    .constrainAs(refDelete) {
                        end.linkTo(refEdit.start, 8.dp)
                        bottom.linkTo(parent.bottom, 8.dp)
                    }
                    .size(22.dp)
                    .clickable {
                        onClickDelete(earning.id)
                    }
            )
        }
    }
}