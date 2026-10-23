def nb_to_liste(nb):
    L = list(str(nb))
    for i in range(len(L)):
        L[i] = int(L[i])
    return L




def npparition_premier_element(L):
    c = L[0]
    L.pop(0)
    nb_app = 1
    while len(L) > 0 and c == L[0]:
        nb_app+=1
        L.pop(0)
    return nb_app, c


def Robinson_termSuivant(nb):
    ans = 0
    nb = nb_to_liste(nb)
    print(nb)
    while len(nb) > 0:
        
        nb_app , c = npparition_premier_element(nb)
        ans = ans*100 + nb_app*10 + c

        print("ans = " + str(ans))

    return ans


def robinson(N):
    ans = 0
    if N == 0:
        return ans
    for i in range(1, N+1):
        ans = Robinson_termSuivant(ans)
    return ans
    

robinson(5)