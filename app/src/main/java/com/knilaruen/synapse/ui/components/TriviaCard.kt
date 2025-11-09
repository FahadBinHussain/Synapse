package com.knilaruen.synapse.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.knilaruen.synapse.data.Category
import com.knilaruen.synapse.data.TriviaFact
import com.knilaruen.synapse.ui.theme.*
import kotlin.math.abs

@Composable
fun TriviaCard(
    fact: TriviaFact?,
    onNextFact: () -> Unit,
    modifier: Modifier = Modifier
) {
    var offsetX by remember { mutableStateOf(0f) }
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "scale"
    )
    
    val infiniteTransition = rememberInfiniteTransition(label = "gradient")
    val gradientOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "gradientOffset"
    )
    
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Animated gradient background
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            AccentGradient1.copy(alpha = 0.15f),
                            AccentGradient2.copy(alpha = 0.15f),
                            AccentGradient3.copy(alpha = 0.15f)
                        ),
                        start = Offset(gradientOffset, gradientOffset),
                        end = Offset(gradientOffset + 1000f, gradientOffset + 1000f)
                    )
                )
        )
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Header
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 40.dp)
            ) {
                Text(
                    text = "SYNAPSE",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 4.sp,
                    color = MaterialTheme.colorScheme.primary
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = "Tiny Trivia, Big Curiosity",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                    letterSpacing = 1.sp
                )
            }
            
            // Fact Card
            AnimatedVisibility(
                visible = fact != null,
                enter = fadeIn(animationSpec = tween(500)) + scaleIn(
                    initialScale = 0.8f,
                    animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
                ),
                exit = fadeOut(animationSpec = tween(300)) + scaleOut(targetScale = 0.8f)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .scale(scale)
                        .pointerInput(Unit) {
                            detectHorizontalDragGestures(
                                onDragStart = { isPressed = true },
                                onDragEnd = {
                                    isPressed = false
                                    if (abs(offsetX) > 200f) {
                                        onNextFact()
                                    }
                                    offsetX = 0f
                                },
                                onDragCancel = {
                                    isPressed = false
                                    offsetX = 0f
                                }
                            ) { change, dragAmount ->
                                change.consume()
                                offsetX += dragAmount
                            }
                        }
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            isPressed = !isPressed
                        },
                    shape = RoundedCornerShape(32.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 2.dp
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        fact?.let {
                            // Category badge
                            CategoryBadge(category = it.category)
                            
                            Spacer(modifier = Modifier.height(24.dp))
                            
                            // Fact text
                            Text(
                                text = it.fact,
                                style = MaterialTheme.typography.headlineSmall,
                                textAlign = TextAlign.Center,
                                lineHeight = 32.sp,
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
            
            // Bottom actions
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = 40.dp)
            ) {
                // Swipe hint
                Text(
                    text = "Swipe or tap for next fact",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                    modifier = Modifier.alpha(
                        animateFloatAsState(
                            targetValue = if (abs(offsetX) > 50f) 0f else 1f,
                            label = "hint"
                        ).value
                    )
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Next button
                FloatingActionButton(
                    onClick = onNextFact,
                    modifier = Modifier.size(64.dp),
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    shape = CircleShape
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Next Fact",
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryBadge(category: Category) {
    val categoryColor = when (category) {
        Category.SCIENCE -> Color(0xFF6366F1)
        Category.NATURE -> Color(0xFF10B981)
        Category.HISTORY -> Color(0xFFF59E0B)
        Category.SPACE -> Color(0xFF8B5CF6)
        Category.ANIMALS -> Color(0xFFEC4899)
        Category.HUMAN_BODY -> Color(0xFFEF4444)
        Category.TECHNOLOGY -> Color(0xFF06B6D4)
        Category.QUIRKY -> Color(0xFFF97316)
        Category.GEOGRAPHY -> Color(0xFF14B8A6)
        Category.LANGUAGE -> Color(0xFFA855F7)
    }
    
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = categoryColor.copy(alpha = 0.15f),
        modifier = Modifier.clip(RoundedCornerShape(20.dp))
    ) {
        Text(
            text = category.name.lowercase().replaceFirstChar { it.uppercase() }.replace("_", " "),
            style = MaterialTheme.typography.labelMedium,
            color = categoryColor,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            letterSpacing = 0.5.sp
        )
    }
}
