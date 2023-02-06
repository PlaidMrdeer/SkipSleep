package com.skipsleep.command.tab;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Tabs {
    public List<String> setTabs(@NotNull String[] args, String[] subCommands) {
        if (args.length > 1) {
            return new ArrayList<>();
        }

        if (args.length == 0) {
            return Arrays.asList(subCommands);
        }

        return Arrays.stream(subCommands).filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
    }
}
