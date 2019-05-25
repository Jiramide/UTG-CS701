#include <stdlib.h>
#include <stdio.h>

#include "singly_linked_list.h"

singly_linked_list_t* singly_linked_list_new()
{
  singly_linked_list_t* newList = malloc(sizeof(singly_linked_list_t));
  newList->data = 0;
  newList->next = NULL;

  return newList;
}

singly_linked_list_t* singly_linked_list_from_array(int data[], size_t len)
{
  if (len == 0)
    return;

  singly_linked_list_t* root = singly_linked_list_new()
  root->data = data[0];

  singly_linked_list_t* curr = root;

  for (size_t idx = 1; idx < len; idx++)
  {
    singly_linked_list_t* next = singly_linked_list_new();
    next->data = data[idx];
    curr->next = next;
    next = curr;
  }

  return root;
}

void singly_linked_list_free(singly_linked_list_t* list)
{
  for (; list != NULL; list = list->next)
    free(list);
}

size_t singly_linked_list_len(singly_linked_list_t* list)
{
  size_t len = 0;
  for (; list != NULL; list = list->next)
    len += 1;

  return len;
}

singly_linked_list_t* singly_linked_list_last(singly_linked_list_t* list)
{
  for (; list != NULL; list = list->next)
    if (list->next == NULL)
      return list;
}

int singly_linked_list_get(singly_linked_list_t* list, size_t idx)
{
  for (; idx > 0; idx--)
    list = list->next;

  return list->data;
}

singly_linked_list_t* singly_linked_list_map(singly_linked_list_t* list, int(*map)(int))
{
  singly_linked_list_t* root = singly_linked_list_new();
  root->data = map(list->data);

  singly_linked_list_t* curr = root;

  for (list = list->next; list != NULL; list = list->next)
  {
    singly_linked_list_t* next = singly_linked_list_new();
    next->data = map(list->data);
    curr->next = next;
    curr = next;
  }

  return root;
}

int singly_linked_list_foldl(singly_linked_list_t* list, int(*fold)(int, int), int acc)
{
  for (; list != NULL; list = list->next)
    acc = fold(acc, list->data);

  return acc;
}

int singly_linked_list_foldr(singly_linked_list_t* list, int(*fold)(int, int), int acc)
{
  if (list == NULL)
    return acc;

  return fold(list->data, singly_linked_list_foldr(list->next, fold, acc))
}
