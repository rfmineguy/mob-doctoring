package com.fischerworks.mobdoctoring.util;

import java.util.HashMap;

public class InjectionTableLookup extends HashMap<String, String> {
    public InjectionTableLookup() {
        super();
        //'simple_dungeon' resides in data/mob_doctoring/inject/
        AddReplaceAlias("pillager_outpost", "simple_dungeon");
        AddReplaceAlias("woodland_mansion", "simple_dungeon");
        AddReplaceAlias("simple_dungeon",   "simple_dungeon");
        AddReplaceAlias("village_toolsmith", "village_toolsmith");
    }

    /**
     * use 'simple_dungeon' for 'pillager_outpost' and 'woodland_mansion' in addition to 'simple_dungeon'.
     */


    public void AddReplaceAlias(String toReplace, String replacement)
    {
        this.put(toReplace, replacement);
    }
}
