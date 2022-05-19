
absoluteDiff:: Int -> Int -> Int
absoluteDiff x y = abs (x - y)

fcfsDistance:: [Int] -> Int
fcfsDistance [] = 0
fcfsDistance (h:hs) = sum (map (\(x, y) -> absoluteDiff x y) (zip (h:hs) hs))

-- new::Int -> [Int] -> (Int ,[Int])
-- new current other =  min (map (\x -> absolute_diff current x) other) + 

-- shortest_seek_first:: [Int] -> Int
-- shortest_seek_first [] = 0
-- shortest_seek_first (p:ps) =  

wordsWhen :: (Char -> Bool) -> String -> [String]
wordsWhen p s = case dropWhile p s of
    "" -> []
    s' -> w : wordsWhen p s''
        where (w, s'') = break p s'

inputProcess:: String -> [Int]
inputProcess userinput = map (\ x -> read x :: Int) (wordsWhen (==',') userinput)

main::IO()
main = do
    userinput <- getLine
    let positions = inputProcess userinput
    print (fcfsDistance positions)
