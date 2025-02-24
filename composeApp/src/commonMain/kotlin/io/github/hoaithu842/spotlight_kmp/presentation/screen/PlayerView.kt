package io.github.hoaithu842.spotlight_kmp.presentation.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import io.github.hoaithu842.spotlight_kmp.AudioProvider
import io.github.hoaithu842.spotlight_kmp.AudioUpdates
import io.github.hoaithu842.spotlight_kmp.domain.model.Song
import io.github.hoaithu842.spotlight_kmp.manager.PlayerState
import io.github.hoaithu842.spotlight_kmp.presentation.component.FullsizePlayer
import io.github.hoaithu842.spotlight_kmp.presentation.component.MinimizedPlayer
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerView(
    scaffoldState: BottomSheetScaffoldState,
    navBarDisplayingChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
// viewModel: PlayerViewModel = hiltViewModel(),
) {
// val currentPosition by viewModel.currentPositionFlow.collectAsStateWithLifecycle(initialValue = 0)
// val playerUiState by viewModel.playerUiState.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()
    val audioPlayerState: MutableState<PlayerState?> = remember { mutableStateOf(null) }

    val currentSong = Song(
        title = "Beautiful In White",
        artists = "Shayne Ward",
        source = "https://thantrieu.com/resources/music/1073419268.mp3",
        image = "https://thantrieu.com/resources/arts/1073419268.webp",
    )
// var isLoading by remember { mutableStateOf(true) }

    AudioProvider(
        audioUpdates = object : AudioUpdates {
            override fun onProgressUpdate(playerState: PlayerState) {
                audioPlayerState.value = playerState
                println("Player state: $playerState")
            }

            override fun onReady() {

            }

            override fun onError(exception: Exception) {

            }

        }
    ) { audioPlayer ->
        AnimatedContent(
            targetState = scaffoldState.bottomSheetState.currentValue,
            label = "",
        ) {
            when (it) {
                SheetValue.Hidden -> {}
                SheetValue.Expanded -> {
                    FullsizePlayer(
                        isPlaying = audioPlayerState.value?.isPlaying ?: false,
                        song = currentSong,
                        currentPosition = audioPlayerState.value?.currentTime ?: 0f,
                        duration = audioPlayerState.value?.duration ?: 0f,
                        onMainFunctionClick = { audioPlayer.play(currentSong.source) },
                        onPrevClick = {},
                        onNextClick = {},
                        onMinimizeClick = {
                            coroutineScope.launch {
                                navBarDisplayingChange(true)
                                delay(100)
                                scaffoldState.bottomSheetState.partialExpand()
                            }
                        }
                    )
                }

                SheetValue.PartiallyExpanded -> {
                    MinimizedPlayer(
                        isPlaying = audioPlayerState.value?.isPlaying ?: false,
                        song = currentSong,
                        currentPosition = audioPlayerState.value?.currentTime ?: 0f,
                        duration = audioPlayerState.value?.duration ?: 0f,
                        onMainFunctionClick = { audioPlayer.play(currentSong.source) },
                        onPlayerClick = {
                            coroutineScope.launch {
                                navBarDisplayingChange(false)
                                scaffoldState.bottomSheetState.expand()
                            }
                        }
                    )
                }
            }
        }
    }
}