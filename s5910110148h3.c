/* To compile me type:  gcc hw3.c -lpthread */

#include <pthread.h>
#include <stdio.h>
#include <string.h>
#include <semaphore.h>

void * threadAdd(void *);

#define NUM_THREADS 13
pthread_t tid[NUM_THREADS];      /* array of thread IDs */
sem_t semB;
double result = 0.0;

main( int argc, char *argv[] )
{
  int i;

  sem_init(&semB,0,1);

  for (i=0; i<NUM_THREADS; i++) {
    pthread_create(&tid[i], NULL, threadAdd, NULL);
  }

  for ( i = 0; i < NUM_THREADS; i++)
    pthread_join(tid[i], NULL);

  printf("This is the main thread: result=%.0f\n", result);

}  /* main */

void * threadAdd(void * parm)
{
   int i;
  sem_wait(&semB);
   printf("I am a Thread %ld in process %d\n",pthread_self(),getpid());
   for(i=0;i<10000;i++) {
       result = result + 2;
   }
  sem_post(&semB);
   pthread_exit(0);
}

