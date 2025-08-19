import React, { useState } from "react";
import axios from "axios";
import { Input, Textarea, Button, Spacer } from "@nextui-org/react";

const CreateUpdateTaskListScreen = () => {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");

  const handleCreateTaskList = async () => {
    try {
      const response = await axios.post("http://localhost:8080/task-lists", {
        title,
        description,
      });
      console.log("Task List Created:", response.data);
      alert("Created successfully!");
    } catch (error) {
      console.error("Error creating task list:", error);
      alert("Failed to create task list");
    }
  };

  return (
    <div className="p-6 max-w-md mx-auto">
      <h2 className="text-2xl font-bold mb-4">Create Task List</h2>

      <Input
        label="Title"
        placeholder="Enter list title"
        value={title}
        onChange={(e) => setTitle(e.target.value)}
      />
      <Spacer y={1} />

      <Textarea
        label="Description"
        placeholder="Enter list description"
        value={description}
        onChange={(e) => setDescription(e.target.value)}
      />
      <Spacer y={2} />

      <Button color="primary" onClick={handleCreateTaskList}>
        Create Task List
      </Button>
    </div>
  );
};

export default CreateUpdateTaskListScreen;
