package com.lunatictiol.compsenewsapp.presentation.article_list.component

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.lunatictiol.compsenewsapp.R
import com.lunatictiol.compsenewsapp.domain.model.Article
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun ArticleCard(
    article: Article,

    )

{  val image = article.multimedia?.get(0)?.url
    val context = LocalContext.current
      val intent = remember { Intent(
        ACTION_VIEW, Uri.parse(article.url))
       }

    var expand by remember{ mutableStateOf(false) }
     Column(
         modifier = Modifier
             .fillMaxWidth()
             .padding(10.dp)
             .wrapContentHeight()

             .clickable {
                 expand = true
             }
             .animateContentSize(
                 animationSpec = spring(
                     dampingRatio = Spring.DampingRatioMediumBouncy,
                     stiffness = Spring.StiffnessLow
                 )
             )
         ,
         verticalArrangement = Arrangement.Center,
         horizontalAlignment = Alignment.Start
     ){     
            AsyncImage(model = image, contentDescription = "image"
                , placeholder = painterResource(
                 id = R.drawable.ic_launcher_background
                ),
                modifier = Modifier.requiredHeightIn(max = 350.dp).fillMaxWidth()

            )
            

             Text(text = article.title,
                 style = MaterialTheme.typography.headlineLarge,
                 maxLines = if (expand) 4 else 2,
                 minLines = 1

             )

             Text(text = convertDateString(article.published_date) ,
                 style = MaterialTheme.typography.titleMedium

             )

         Spacer(modifier = Modifier.height(5.dp))
         Text(text = "Section = " + article.section.toUpperCase(),
             style = MaterialTheme.typography.titleSmall

         )

         if (expand){
             Row(modifier =Modifier.fillMaxWidth(),
                 horizontalArrangement = Arrangement.SpaceBetween
             ) {
                      Column(modifier=Modifier.weight(.7f)) {
                          Text(text = article.abstract,
                              style = MaterialTheme.typography.bodyLarge
                          )

                          Text(text = "Url = " + article.url,
                              color = Color.Blue,
                              style = MaterialTheme.typography.bodyMedium,

                              modifier = Modifier.clickable {
                                  context.startActivity(intent)
                              }
                          )
                       }
                 IconButton(onClick = { expand = !expand }) {
                     Icon(
                         imageVector = Icons.Filled.KeyboardArrowUp,
                         contentDescription = if (expand) {
                             "(string.show_less)"
                         } else {
                             "(string.show_more)"
                         }

                     )
                 }
             }





         }
     }




}
fun convertDateString(dateString: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.US)
    val outputFormat = SimpleDateFormat("d/y/M", Locale.US)

    val date = inputFormat.parse(dateString)
    return outputFormat.format(date)
}


