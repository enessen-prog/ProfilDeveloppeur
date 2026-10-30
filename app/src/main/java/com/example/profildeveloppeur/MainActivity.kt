package com.example.profildeveloppeur

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.profildeveloppeur.ui.theme.ProfilDeveloppeurTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfilDeveloppeurTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProfileScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    ProfileCard(
        name = "Enes Sen",
        role = "Développeur Android junior",
        description = "Étudiant en master informatique, passionné par le développement mobile et les interfaces modernes avec Jetpack Compose.",
        skills = listOf("Kotlin", "Jetpack Compose", "Git", "SQL"),
        isAvailable = true,
        contact = "enes.sen@exemple.fr",
        onContactClick = {},
        onPortfolioClick = {},
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Composable
fun ProfileCard(
    name: String,
    role: String,
    description: String,
    skills: List<String>,
    isAvailable: Boolean,
    contact: String,
    onContactClick: () -> Unit,
    onPortfolioClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            ProfileHeader(
                name = name,
                role = role
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 12.dp)
            )
            SkillList(
                skills = skills,
                modifier = Modifier.padding(top = 16.dp)
            )
            AvailabilityInfo(
                isAvailable = isAvailable,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
            ContactInfo(
                contact = contact,
                modifier = Modifier.padding(top = 12.dp)
            )
            ContactButton(
                text = "Contacter",
                onClick = onContactClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
            PortfolioLink(
                text = "Voir le portfolio",
                onClick = onPortfolioClick,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun ProfileHeader(
    name: String,
    role: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = role,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun SkillList(
    skills: List<String>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "Compétences",
            style = MaterialTheme.typography.titleSmall
        )
        skills.forEach { skill ->
            SkillItem(
                skill = skill,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp)
            )
        }
    }
}

@Composable
fun SkillItem(
    skill: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surface
    ) {
        Text(
            text = skill,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

@Composable
fun AvailabilityInfo(
    isAvailable: Boolean,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = if (isAvailable) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            MaterialTheme.colorScheme.errorContainer
        }
    ) {
        Text(
            text = if (isAvailable) {
                "Disponible pour une alternance"
            } else {
                "Non disponible actuellement"
            },
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(12.dp)
        )
    }
}

@Composable
fun ContactInfo(
    contact: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Contact : $contact",
        style = MaterialTheme.typography.bodySmall,
        modifier = modifier
    )
}

@Composable
fun ContactButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(text = text)
    }
}

@Composable
fun PortfolioLink(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .semantics {
                role = Role.Button
                contentDescription = "Ouvrir le portfolio du développeur"
            }
            .padding(12.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfilDeveloppeurTheme {
        ProfileScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileCardAndroidJuniorPreview() {
    ProfilDeveloppeurTheme {
        ProfileCard(
            name = "Léa Martin",
            role = "Développeuse Android junior",
            description = "Recherche une alternance pour concevoir des applications mobiles accessibles.",
            skills = listOf("Kotlin", "Jetpack Compose", "Material 3"),
            isAvailable = true,
            contact = "lea.martin@exemple.fr",
            onContactClick = {},
            onPortfolioClick = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileCardFullStackUnavailablePreview() {
    ProfilDeveloppeurTheme {
        ProfileCard(
            name = "Karim Benali",
            role = "Développeur full-stack",
            description = "Cinq ans d'expérience sur des applications web et des API REST.",
            skills = listOf("TypeScript", "React", "Node.js", "PostgreSQL", "Docker"),
            isAvailable = false,
            contact = "karim.benali@exemple.fr",
            onContactClick = {},
            onPortfolioClick = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}
