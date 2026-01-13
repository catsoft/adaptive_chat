package com.catsoft.adaptivechat.agent.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.catsoft.adaptivechat.agent.domain.Agent
import com.catsoft.adaptivechat.agent.viewmodel.AgentsViewModel
import com.catsoft.adaptivechat.ui.kit.modifier.colors
import com.catsoft.adaptivechat.ui.kit.modifier.taC
import com.catsoft.adaptivechat.ui.kit.modifier.typo
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AgentsScreen(viewModel: AgentsViewModel = koinViewModel()) {
    val agents by viewModel.agents.collectAsStateWithLifecycle()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Select an Agent") },
                navigationIcon = {
                    IconButton(onClick = {  }) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colors.primaryContainer
                )
            )
        }
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(agents) { agent ->
                AgentCard(
                    agent = agent,
                    onClick = {
                    }
                )
            }
        }
    }
}

@Composable
fun AgentCard(
    agent: Agent,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        color = colors.secondaryContainer,
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = agent.icon,
                style = typo.displayLarge,
                textAlign = taC
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = agent.name,
                style = typo.titleMedium,
                textAlign = taC,
                color = colors.onSecondaryContainer
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = agent.description,
                style = typo.bodySmall,
                textAlign = taC,
                color = colors.onSecondaryContainer.copy(alpha = 0.7f)
            )
        }
    }
}
