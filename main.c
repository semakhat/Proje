#include <stdio.h>
#include <stdlib.h>

int main()
{
    FILE *dosya;
    int puan[8][10];
    int i, j;
    if((dosya=fopen("puanlar.txt","r"))!=NULL)
    {
        for(i=0; i<10; i++ )
        {
            for(j=0; j<8; j++)
            {
                fscanf(dosya,"%d", &puan[j][i]);
            }
        }
        float toplam[8] = {0};
        for(i=0; i<8; i++)
        {
            for(j=0; j<10; j++)
            {
                toplam[i] = toplam[i] + puan[i][j];
            }
        }
        float ortalama[8];
        for(i=0; i<8; i++)
        {
            ortalama[i] = toplam[i] / 10;
        }
        float max = ortalama[0];
        int peynir_no;
        for(i=0; i<8; i++)
        {
            if(ortalama[i]>max)
            {
                max = ortalama[i];
                peynir_no=i;
            }
        }
        int aile_puan=5, aile_no;
        for(i=0; i<10; i++)
        {
            if(aile_puan>puan[peynir_no][i])
            {
                aile_puan=puan[peynir_no][i];
                aile_no=i;
            }
        }
        FILE *sonuc;
        if((sonuc=fopen("sonuc.txt","w"))!=NULL)
        {
            fprintf(sonuc, "En cok begenilen urun no: %d\nOrtalamasi: %.1f\n",peynir_no+1, max);
            fprintf(sonuc, "En az begenen aile no: %d",aile_no+1);
        }
        fclose(sonuc);
    }
    fclose(dosya);
    return 0;
}
