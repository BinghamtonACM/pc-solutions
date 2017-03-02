-module(taylor).
-export([main/0]).



chunk([H1, H2, H3 | T]) ->
    [[H1, H2, H3] | chunk([H2, H3 | T])];
chunk(L) when is_list(L) ->
    [].

solve(C) ->
    {ok, _} = io:fread("", "~s"),
    {ok, [S]} = io:fread("", "~s"),
    M = [{'TTT', 0},
         {'TTH', 0},
         {'THT', 0},
         {'THH', 0},
         {'HTT', 0},
         {'HTH', 0},
         {'HHT', 0},
         {'HHH', 0}
        ],
    Windows = lists:map(fun list_to_atom/1, chunk(S)),
    FreqList = lists:foldl(fun(X, Freq) ->
                            {K, Y} = lists:keyfind(X, 1, Freq),
                            lists:keyreplace(K, 1, Freq, {K, Y+1})
                           end, M, Windows),
    io:fwrite("~b", [C]),
    lists:foreach(fun({_, Count}) -> io:fwrite(" ~b", [Count]) end, FreqList),
    io:nl().

main() ->
    {ok, [N]} = io:fread("", "~d"),
    lists:foreach(fun solve/1, lists:seq(1, N)).
