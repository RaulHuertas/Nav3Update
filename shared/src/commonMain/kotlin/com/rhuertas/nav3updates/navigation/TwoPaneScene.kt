package com.rhuertas.nav3updates.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.scene.Scene
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfoV2
import androidx.navigation3.scene.SceneStrategy
import androidx.navigation3.scene.SceneStrategyScope
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowSizeClass.Companion.WIDTH_DP_MEDIUM_LOWER_BOUND
import com.rhuertas.nav3updates.navigation.TwoPaneScene.Companion.TWO_PANE_KEY

class TwoPaneScene <T:Any>(
   override val key: Any,
    override val previousEntries : List<NavEntry<T>>,
    val firstEntry: NavEntry<T>,
    val secondEntry: NavEntry<T>
): Scene<T>{
    override val entries : List<NavEntry<T>>
        get() = listOf(firstEntry, secondEntry)

    override val content: @Composable (()->Unit)
        get() = {
            Row(
                modifier = Modifier.fillMaxSize()
            ){
                Box(modifier = Modifier.weight(0.3f)) {
                    firstEntry.Content()
                }
                Box(modifier = Modifier.weight(0.7f)) {
                    secondEntry.Content()
                }
            }
        }

    companion object{
        const val TWO_PANE_KEY = "TwoPaneKey"
        fun twoPane() = mapOf(TWO_PANE_KEY to true)
    }

}
data class SinglePaneScene<T : Any>(
    override val key: Any,
    val entry: NavEntry<T>,
    override val previousEntries: List<NavEntry<T>>,
) : Scene<T> {
    override val entries: List<NavEntry<T>> = listOf(entry)
    override val content: @Composable () -> Unit = { entry.Content() }
}

public class SinglePaneSceneStrategy<T : Any> : SceneStrategy<T> {
    override fun SceneStrategyScope<T>.calculateScene(entries: List<NavEntry<T>>): Scene<T>? {
        return SinglePaneScene(
            key = entries.last().contentKey,
            entry = entries.last(),
            previousEntries = entries.dropLast(1)
        )
    }
}

class TwoPaneSceneStrategy<T:Any> (
    private val windowSizeClass : WindowSizeClass
): SceneStrategy<T> {
    override fun SceneStrategyScope<T>.calculateScene(
        entries: List<NavEntry<T>>,
        //onBack: (count:Int) -> Unit
    ): Scene<T>? {
        if(!windowSizeClass.isWidthAtLeastBreakpoint(WIDTH_DP_MEDIUM_LOWER_BOUND)){
            return null
        }
        val lastTwoEntries = entries.takeLast(2)
        val hasTwoPaneKey = lastTwoEntries.all{
            it.metadata.containsKey(TWO_PANE_KEY) && it.metadata[TWO_PANE_KEY] == true
        }

        return if(lastTwoEntries.size == 2 && hasTwoPaneKey){
           val firstEntry = lastTwoEntries.first()
           val secondEntry = lastTwoEntries.last()
            TwoPaneScene(
                //key = TwoPaneScene.TWO_PANE_KEY,
                key = firstEntry.contentKey to secondEntry.contentKey,
                previousEntries = entries.dropLast(1),
                firstEntry = firstEntry,
                secondEntry = secondEntry
            )
        }else {
            null
        }

    }
}