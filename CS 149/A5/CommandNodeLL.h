/**
 *CommandNode.h
 *Author: Bill Andreopoulos
 *The struct for a Command Node and function prototypes for a linked list of commands
 *CS149 assignments 3-5 usage only
**/


typedef struct command_struct {
   char *command;
   int index;
   int PID;
   int starttime;
   struct command_struct* nextCommandPtr;
} CommandNode;


void CreateCommandNode(CommandNode* thisNode, char *cmd, int ind, CommandNode* nextCmd);
void InsertCommandAfter(CommandNode* thisNode, CommandNode* newNode);
CommandNode* GetNextCommand(CommandNode* thisNode);
CommandNode* FindCommand(CommandNode* cmd, int pid);
 
