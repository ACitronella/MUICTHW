for i in range(3):
    for j in range(3):
        p = int(420/3)
        pltimg.imsave(f"{i}{j}.png", img[p*i: p+p*i, p*j: p+p*j])