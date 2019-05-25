#include <stdlib.h>

#pragma once

typedef struct singly_linked_list_t {
  int data;
  struct singly_linked_list_t next;
} singly_linked_list_t;

singly_linked_list_t* singly_linked_list_new();
singly_linked_list_t* singly_linked_list_from_array(int data[]);
void singly_linked_list_free(singly_linked_list_t* list);

size_t singly_linked_list_len(singly_linked_list_t* list);
singly_linked_list_t* singly_linked_list_last(singly_linked_list_t* list);
int singly_linked_list_get(singly_linked_list_t* list, size_t idx);

singly_linked_list_t* singly_linked_list_map(singly_linked_list_t* list, int(*map)(int));
int singly_linked_list_foldl(singly_linked_list_t* list, int(*fold)(int, int), int acc);
int singly_linked_list_foldr(singly_linked_list_t* list, int(*fold)(int, int), int acc);
